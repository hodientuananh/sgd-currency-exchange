package com.hodientuananh.sgdcurrencyexchange.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "EXCHANGE_CURRENCY_HISTORY")
public class ExchangeCurrencyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EXCHANGE_CURRENCY_SEQ")
    @SequenceGenerator(sequenceName = "EXCHANGE_CURRENCY_SEQ", allocationSize = 1, name = "EXCHANGE_CURRENCY_SEQ")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "SOURCE_CURRENCY")
    private String sourceCurrency;

    @Column(name = "TARGET_CURRENCY")
    private String targetCurrency;

    @Column(name = "EXCHANGE_RATE")
    private BigDecimal exchangeRate;

    @Column(name = "EXCHANGE_AMOUNT")
    private BigDecimal exchangeAmount;

    @Column(name = "SOURCE_AMOUNT")
    private BigDecimal sourceAmount;

    @Column(name = "CREATED_DATE")
    private Date createdDate;
}
