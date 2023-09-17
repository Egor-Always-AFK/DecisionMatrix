package ru.sbrf.demo.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sbrf.demo.annotation.Factor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Decision {
    @Factor
    private String currency;

    @Factor
    private Interval<Long> amount;

    @Factor
    private Interval<Long> term;

    @ru.sbrf.demo.annotation.Decision
    private Double rate;
}
