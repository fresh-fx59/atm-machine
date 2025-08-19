package org.example;

import org.example.enums.Denomination;
import org.example.exception.ATMProcessorException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.constants.Constants.MINIMUM_AMOUNT_IN_RUB;

//todo make bigdecimal instead of integer, add check for situation where we can't withdraw cause kupur count
public class ATMProcessorImpl {
    private Map<Denomination, Integer> money;
    private Integer sumOfAmount = 0;

    public void initializeATM(Map<Denomination, Integer> money) {
        this.money = money;
        money.forEach((key, value) -> sumOfAmount += (key.getValue() * value));
    }

    public Map<Denomination, Integer> withdrawMoney(Integer moneyToWithdraw) {
        Map<Denomination, Integer> banknotesToBeWithdraw = new HashMap<>();
        AtomicInteger moneyToWithdrawFinal = new AtomicInteger(moneyToWithdraw);


        if (moneyToWithdraw == null)
            throw new ATMProcessorException("moneyToWithdraw couldn't be null");

        if (moneyToWithdraw > sumOfAmount)
            throw new ATMProcessorException("moneyToWithdraw is greater than sum in ATM");

        if ((moneyToWithdraw % MINIMUM_AMOUNT_IN_RUB) > 0)
            throw new ATMProcessorException("moneyToWithdraw should be kratno " + MINIMUM_AMOUNT_IN_RUB);

        Arrays.stream(Denomination
                .values())
                .forEach(
                        value -> {
                            int amount = value.getValue();
                            if (moneyToWithdrawFinal.get() >= amount) {
                                int banknotesNeeded = moneyToWithdrawFinal.get() / amount;
                                //проверить сколько у нас купюр данного номинала в банкомате
                                int currentBanknotesCountInAtm = money.get(value) == null ? 0 : money.get(value);
                                if (currentBanknotesCountInAtm > 0) {
                                    int minBanknoteCount = Math.min(currentBanknotesCountInAtm, banknotesNeeded);
                                    int promezhutochnajaSumma = moneyToWithdrawFinal.get() - (minBanknoteCount * amount);
                                    money.put(value, currentBanknotesCountInAtm - minBanknoteCount);
                                    banknotesToBeWithdraw.put(value, minBanknoteCount);
                                    moneyToWithdrawFinal.getAndSet(promezhutochnajaSumma);
                                }
                            }
                        }
                );

        return banknotesToBeWithdraw;
    }
}
