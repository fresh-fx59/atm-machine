package org.example;

import org.example.enums.Denomination;

import java.util.Map;

public interface ATMProcessor {
    void initializeATM(Map<Denomination, Integer> money);
    void withdrawMoney(Map<Denomination, Integer> moneyToWithdraw);;
}
