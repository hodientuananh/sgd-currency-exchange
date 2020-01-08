package com.hodientuananh.sgdcurrencyexchange.exceptions;

import org.springframework.stereotype.Component;

public class SgdExceptionMapper {
    @Component
    static class SgdExceptionToErrorCode implements ExceptionToErrorCode {

        @Override
        public ErrorCode toErrorCode(ServiceException exception) {
            return exception.getErrorCode();
        }
    }
}
