package org.example;

import org.example.enums.Denomination;
import org.example.model.ATM;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.enums.Denomination.RUB100;
import static org.example.enums.Denomination.RUB500;

public class ATMProcessorImplTest {
    ATMProcessorImpl atmProcessorImpl = new ATMProcessorImpl();

    @Test
    public void withdrawMoneyTest() {
        //given
        Map<Denomination, Integer> moneyInAtm = new HashMap<>() {{
            put(RUB100, 10);
            put(RUB500, 20);
        }};
        Map<Denomination, Integer> expectedResult = new HashMap<>() {{
            put(RUB100, 10);
            put(RUB500, 20);
        }};
        atmProcessorImpl.initializeATM(moneyInAtm);

        //when
        Map<Denomination, Integer> actualResult = atmProcessorImpl.withdrawMoney(11000);

        //then
        assertThat(expectedResult).containsExactlyInAnyOrderEntriesOf(actualResult);
    }

}
