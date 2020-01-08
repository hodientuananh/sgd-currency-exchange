package com.hodientuananh.sgdcurrencyexchange.constant;

import com.hodientuananh.sgdcurrencyexchange.model.Currency;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExchangeRate {

    public static final Set<Currency> rates = new HashSet<>(
            Arrays.asList(
                    new Currency(1, "SGD", BigDecimal.valueOf(1)),
                    new Currency(2, "USD", BigDecimal.valueOf(0.7407996817)),
                    new Currency(3, "CAD", BigDecimal.valueOf(0.9628008753)),
                    new Currency(4, "HKD", BigDecimal.valueOf(5.7579736092)),
                    new Currency(5, "ISK", BigDecimal.valueOf(91.0417081095)),
                    new Currency(6, "PHP", BigDecimal.valueOf(37.6845036801)),
                    new Currency(7, "DKK", BigDecimal.valueOf(4.9553080034)),
                    new Currency(8, "HUF", BigDecimal.valueOf(219.6671308269)),
                    new Currency(9, "CZK", BigDecimal.valueOf(16.760161793)),
                    new Currency(10, "GBP", BigDecimal.valueOf(0.5648365493)),
                    new Currency(11, "RON", BigDecimal.valueOf(3.1680923016)),
                    new Currency(12, "SEK", BigDecimal.valueOf(6.9904515616)),
                    new Currency(13, "IDR", BigDecimal.valueOf(10278.5955838472)),
                    new Currency(14, "INR", BigDecimal.valueOf(53.222597971)),
                    new Currency(15, "BRL", BigDecimal.valueOf(3.0253298853)),
                    new Currency(16, "RUB", BigDecimal.valueOf(45.9237451097)),
                    new Currency(17, "HRK", BigDecimal.valueOf(4.9385319276)),
                    new Currency(18, "JPY", BigDecimal.valueOf(80.3328691731)),
                    new Currency(19, "THB", BigDecimal.valueOf(22.4275578543)),
                    new Currency(20, "CHF", BigDecimal.valueOf(0.7194483124)),
                    new Currency(21, "EUR", BigDecimal.valueOf(0.6630860023)),
                    new Currency(22, "MYR", BigDecimal.valueOf(3.0320933625)),
                    new Currency(23, "BGN", BigDecimal.valueOf(1.2968636032)),
                    new Currency(24, "TRY", BigDecimal.valueOf(4.4267621511)),
                    new Currency(25, "CNY", BigDecimal.valueOf(5.1430276507)),
                    new Currency(26, "NOK", BigDecimal.valueOf(6.534579935)),
                    new Currency(27, "NZD", BigDecimal.valueOf(1.1161726676)),
                    new Currency(28, "ZAR", BigDecimal.valueOf(10.5910748624)),
                    new Currency(29, "MXN", BigDecimal.valueOf(13.9813009747)),
                    new Currency(30, "AUD", BigDecimal.valueOf(1.0775810623)),
                    new Currency(31, "ILS", BigDecimal.valueOf(2.5681983953)),
                    new Currency(32, "KRW", BigDecimal.valueOf(863.6562562164)),
                    new Currency(33, "PLN", BigDecimal.valueOf(2.8152642398))
            )
    );

}
