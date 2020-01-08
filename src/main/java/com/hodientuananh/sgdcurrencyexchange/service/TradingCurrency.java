package com.hodientuananh.sgdcurrencyexchange.service;

import com.hodientuananh.sgdcurrencyexchange.model.Currency;

import java.math.BigDecimal;

public interface TradingCurrency {

    public BigDecimal getExchangedRate(Currency targetCurrency);

    public BigDecimal getExchangedAmount(Currency targetCurrency, BigDecimal sourceAmount);
}
