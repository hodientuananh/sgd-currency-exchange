package com.hodientuananh.sgdcurrencyexchange.exceptions;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class ErrorCodes {
    private final ApplicationContext context;

    ErrorCodes(ApplicationContext context) {
        this.context = context;
    }

    public ErrorCode of(ServiceException exception) {
        return implementations()
                .findFirst()
                .map(impl -> impl.toErrorCode(exception))
                .orElse(ErrorCode.UnknownErrorCode.INSTANCE);
    }

    private Stream<ExceptionToErrorCode> implementations() {
        return context.getBeansOfType(ExceptionToErrorCode.class).values().stream();
    }
}
