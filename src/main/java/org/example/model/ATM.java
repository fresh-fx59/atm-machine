package org.example.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.example.enums.Denomination;

import java.util.Map;

@RequiredArgsConstructor
@Getter
@Setter
public class ATM {
    private final Map<Denomination, Integer> money;
}
