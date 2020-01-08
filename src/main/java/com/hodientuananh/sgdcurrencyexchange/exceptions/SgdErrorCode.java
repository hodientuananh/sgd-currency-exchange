package com.hodientuananh.sgdcurrencyexchange.exceptions;

import org.springframework.http.HttpStatus;

public class SgdErrorCode implements ErrorCode {
    private final String code;
    private final HttpStatus httpStatus;
    private final Object[] params;

    public SgdErrorCode(String code, HttpStatus httpStatus, Object[] params) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.params = params;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public Object[] params() {
        return params;
    }
}
