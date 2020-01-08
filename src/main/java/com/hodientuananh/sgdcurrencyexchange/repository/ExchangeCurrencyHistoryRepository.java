package com.hodientuananh.sgdcurrencyexchange.repository;

import com.hodientuananh.sgdcurrencyexchange.domain.ExchangeCurrencyHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeCurrencyHistoryRepository extends JpaRepository<ExchangeCurrencyHistory, Long> {
}
