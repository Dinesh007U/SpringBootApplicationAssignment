package com.application.customer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateConflictException extends RuntimeException {

    public DuplicateConflictException(String message) {
        super(message);
    }
}
