package com.hodientuananh.sgdcurrencyexchange.service;

import com.hodientuananh.sgdcurrencyexchange.model.Currency;

import java.math.BigDecimal;

public interface TradingCurrency {

    public BigDecimal getExchangedRate(String targetCurrency);

    public BigDecimal getExchangedAmount(String targetCurrency, BigDecimal sourceAmount);
}
