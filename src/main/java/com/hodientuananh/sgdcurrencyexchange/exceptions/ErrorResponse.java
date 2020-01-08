package com.hodientuananh.sgdcurrencyexchange.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@Data
public class ErrorResponse {

    private final int statusCode;
    private final String reasonPhrase;
    private final List<ApiError> errors;

    private ErrorResponse(int statusCode, String reasonPhrase, List<ApiError> errors) {
        if (statusCode < 400 || statusCode > 600)
            throw new IllegalArgumentException("Error Status codes should be between 400 and 599");

        if (reasonPhrase ==  null || reasonPhrase.trim().isEmpty())
            throw new IllegalArgumentException("HTTP Response reason phrase can't be null or blank");

        if (errors == null || errors.isEmpty())
            throw new IllegalArgumentException("Errors list can't be null or empty");

        this.statusCode = statusCode;
        this.reasonPhrase = reasonPhrase;
        this.errors = errors;
    }

    public static ErrorResponse ofErrors(HttpStatus status, List<ApiError> errors) {
        return new ErrorResponse(status.value(), status.getReasonPhrase(), errors);
    }

    public static ErrorResponse of(HttpStatus status, ApiError error) {
        return ofErrors(status, Collections.singletonList(error));
    }

    @Data
    public static class ApiError {
        /**
         * The error code
         */
        private final String code;

        /**
         * Possibly localized error message
         */
        private final String message;

        public ApiError(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }
}
