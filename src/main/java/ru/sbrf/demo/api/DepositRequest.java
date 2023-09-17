package ru.sbrf.demo.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sbrf.demo.annotation.Factor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {
    @Factor
    private String currency;

    @Factor
    private long amount;

    @Factor
    private int term;
}
