package com.hodientuananh.sgdcurrencyexchange.service.impl;

import com.hodientuananh.sgdcurrencyexchange.constant.Constants;
import com.hodientuananh.sgdcurrencyexchange.constant.ExchangeRate;
import com.hodientuananh.sgdcurrencyexchange.exceptions.SgdExceptionService;
import com.hodientuananh.sgdcurrencyexchange.model.Currency;
import com.hodientuananh.sgdcurrencyexchange.service.TradingCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;

@Service
@Slf4j
public class ExchangeCurrency implements TradingCurrency {

    @Override
    public BigDecimal getExchangedRate(Currency targetCurrency) {
        try {
            log.info(Constants.LOG_BEGIN_SERVICE + "getExchangedRate");

            for (Iterator<Currency> it = ExchangeRate.rates.iterator(); it.hasNext(); ) {
                Currency currency = it.next();
                if (currency.getName().equalsIgnoreCase(targetCurrency.getName()))
                    return currency.getExchangedRate();
            }

            throw new SgdExceptionService("TARGET_CURRENCY_NOT_FOUND", new Object[]{targetCurrency.getName()});
        } finally {
            log.info(Constants.LOG_END_SERVICE + "getExchangedRate");
        }

    }

    @Override
    public BigDecimal getExchangedAmount(Currency targetCurrency, BigDecimal sourceAmount) {
         try {
            log.info(Constants.LOG_BEGIN_SERVICE + "getExchangedAmount");

             return getExchangedRate(targetCurrency).multiply(sourceAmount).setScale(6, RoundingMode.HALF_DOWN);
         } finally {
            log.info(Constants.LOG_END_SERVICE + "getExchangedAmount");
        }
    }
}
