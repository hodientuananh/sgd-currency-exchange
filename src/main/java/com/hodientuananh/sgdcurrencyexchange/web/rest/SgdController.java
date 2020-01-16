package com.hodientuananh.sgdcurrencyexchange.web.rest;

import com.hodientuananh.sgdcurrencyexchange.constant.ApiURL;
import com.hodientuananh.sgdcurrencyexchange.constant.Constants;
import com.hodientuananh.sgdcurrencyexchange.domain.ExchangeCurrencyHistory;
import com.hodientuananh.sgdcurrencyexchange.exceptions.ErrorCode;
import com.hodientuananh.sgdcurrencyexchange.exceptions.ErrorCodes;
import com.hodientuananh.sgdcurrencyexchange.exceptions.ErrorResponse;
import com.hodientuananh.sgdcurrencyexchange.exceptions.SgdExceptionService;
import com.hodientuananh.sgdcurrencyexchange.model.Currency;
import com.hodientuananh.sgdcurrencyexchange.model.response.ExchangeCurrencyResponse;
import com.hodientuananh.sgdcurrencyexchange.repository.ExchangeCurrencyHistoryRepository;
import com.hodientuananh.sgdcurrencyexchange.service.TradingCurrency;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Slf4j
@RestController
@Validated
@RequestMapping({ApiURL.EXCHANGE_CURRENCY})
public class SgdController {

    private static final String NO_MESSAGE_AVAILABLE = "No message available";

    @Autowired
    TradingCurrency tradingCurrency;

    @Autowired
    ExchangeCurrencyHistoryRepository exchangeCurrencyHistoryRepository;

    @Autowired
    MessageSource apiErrorMessageSource;

    @Autowired
    ErrorCodes errorCodes;

    @ExceptionHandler(SgdExceptionService.class)
    ResponseEntity<ErrorResponse> handleServiceExceptions(SgdExceptionService exception, Locale locale) {
        ErrorCode errorCode = errorCodes.of(exception);
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.httpStatus(), toApiError(errorCode, locale));

        return ResponseEntity.status(errorCode.httpStatus()).body(errorResponse);
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException exception, Locale locale) {
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();

        List<ErrorResponse.ApiError> apiErrors = constraintViolations.stream()
                .map(ConstraintViolation::getMessage)
                .map(this::validationErrorCode)
                .map(code -> toApiError(code, locale))
                .collect(toList());
        return new ResponseEntity<>(
                ErrorResponse.ofErrors(HttpStatus.BAD_REQUEST, apiErrors)
                , new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception,
                                                                    Locale locale) {
        Stream<ObjectError> errors = exception.getBindingResult().getAllErrors().stream();
        List<ErrorResponse.ApiError> apiErrors = errors
                .map(ObjectError::getDefaultMessage)
                .map(this::validationErrorCode)
                .map(code -> toApiError(code, locale))
                .collect(toList());

        return ResponseEntity.badRequest().body(ErrorResponse.ofErrors(HttpStatus.BAD_REQUEST, apiErrors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleServerExceptions(Exception ex, Locale locale) {
        ErrorCode errorCode = new ErrorCode() {

            @Override
            public Object[] params() {
                return new Object[] { ex.getMessage() };
            }

            @Override
            public HttpStatus httpStatus() {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public String code() {
                return "MI_INTERNAL_SERVER_ERROR";
            }
        };
        ErrorResponse errorResponse = ErrorResponse.of(errorCode.httpStatus(), toApiError(errorCode, locale));

        return ResponseEntity.status(errorCode.httpStatus()).body(errorResponse);
    }

    private ErrorResponse.ApiError toApiError(ErrorCode errorCode, Locale locale) {
        String message;
        try {
            message = apiErrorMessageSource.getMessage(errorCode.code(), errorCode.params(), locale);
        } catch (NoSuchMessageException e) {
            message = NO_MESSAGE_AVAILABLE;
        }

        return new ErrorResponse.ApiError(errorCode.code(), message);
    }

    private ErrorCode validationErrorCode(final String errorCode) {
        return new ErrorCode() {
            @Override
            public String code() {
                return errorCode;
            }

            @Override
            public HttpStatus httpStatus() {
                return HttpStatus.BAD_REQUEST;
            }

            @Override
            public Object[] params() {
                return null;
            }
        };
    }

    @GetMapping
    public ResponseEntity<ExchangeCurrencyResponse> getExchangeRateAndAmount(@RequestParam(name = "sgdAmount") String sgdAmount, @RequestParam(name = "targetCurrency") String targetCurrency) {
        try {
            log.info(Constants.LOG_BEGIN_API + "getExchangeRateAndAmount");

            ExchangeCurrencyResponse res = new ExchangeCurrencyResponse();

            res.setSourceCurrency("SGD");
            res.setTargetCurrency(targetCurrency);
            res.setExchangeAmount(tradingCurrency.getExchangedAmount(targetCurrency, new BigDecimal(sgdAmount)));
            res.setExchangeRate(tradingCurrency.getExchangedRate(targetCurrency));
            res.setSourceAmount(new BigDecimal(sgdAmount));

            ExchangeCurrencyHistory his = new ExchangeCurrencyHistory();
            his.setCreatedDate(new Date());
            his.setTargetCurrency(res.getTargetCurrency());
            his.setSourceCurrency(res.getSourceCurrency());
            his.setExchangeAmount(res.getExchangeAmount());
            his.setExchangeRate(res.getExchangeRate());
            his.setSourceAmount(res.getSourceAmount());

            res.setId(exchangeCurrencyHistoryRepository.save(his).getId());

            return ResponseEntity.ok().body(res);
        } finally {
            log.info(Constants.LOG_END_API + "getExchangeRateAndAmount");
        }
    }
}
