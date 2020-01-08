package com.hodientuananh.sgdcurrencyexchange.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
public class ExchangeCurrencyResponse {

    private Long id;

    private String sourceCurrency;

    private String targetCurrency;

    private BigDecimal exchangeRate;

    private BigDecimal exchangeAmount;

    private BigDecimal sourceAmount;
}
