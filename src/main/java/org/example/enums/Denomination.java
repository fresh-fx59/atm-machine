package org.example.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static org.example.constants.Constants.MINIMUM_AMOUNT_IN_RUB;

@RequiredArgsConstructor
@Getter
public enum Denomination {
    RUB5000("RUB", 5000),
    RUB1000("RUB", 1000),
    RUB500("RUB", 500),
    RUB100("RUB", 100),
    RUB50("RUB", MINIMUM_AMOUNT_IN_RUB),
    ;

    private final String currency;
    private final int value;
}
