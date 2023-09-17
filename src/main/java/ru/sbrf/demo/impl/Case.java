package ru.sbrf.demo.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sbrf.demo.annotation.Factor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Case {
    @Factor
    private String currency;

    @Factor
    private long amount;

    @Factor
    private int term;
}
