package com.hodientuananh.sgdcurrencyexchange.exceptions;

public abstract class ServiceException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public abstract ErrorCode getErrorCode();

}
