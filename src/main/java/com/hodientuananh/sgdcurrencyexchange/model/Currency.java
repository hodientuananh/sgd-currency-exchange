package com.hodientuananh.sgdcurrencyexchange.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class Currency {

    private int id;

    private String name;

    private BigDecimal exchangedRate;
}
