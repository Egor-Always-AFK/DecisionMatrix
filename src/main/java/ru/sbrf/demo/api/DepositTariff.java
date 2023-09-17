package ru.sbrf.demo.api;

import lombok.Builder;
import lombok.Data;
import ru.sbrf.demo.annotation.Decision;
import ru.sbrf.demo.annotation.Factor;
import ru.sbrf.demo.impl.Interval;

@Data
@Builder
public class DepositTariff {
    @Factor
    private String currency;

    @Factor
    private Interval<Long> amount;

    @Factor
    private Interval<Long> term;

    @Decision
    private Double rate;
}
