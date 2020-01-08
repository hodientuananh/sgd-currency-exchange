package com.hodientuananh.sgdcurrencyexchange.exceptions;

public interface ExceptionToErrorCode {

    ErrorCode toErrorCode(ServiceException exception);
}
