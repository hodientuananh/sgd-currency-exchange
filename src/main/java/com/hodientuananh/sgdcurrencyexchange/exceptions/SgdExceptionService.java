package com.hodientuananh.sgdcurrencyexchange.exceptions;

import org.springframework.http.HttpStatus;

public class SgdExceptionService extends ServiceException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String code;
    private Object[] params;

    public SgdExceptionService(String code, Object[] params) {
        this.code = code;
        this.params = params;
    }

    @Override
    public ErrorCode getErrorCode() {
        return new SgdErrorCode(this.code, HttpStatus.BAD_REQUEST, this.params);
    }
}
