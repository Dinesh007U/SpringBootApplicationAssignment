
package com.application.customer.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoValuePresentException extends RuntimeException {
    public NoValuePresentException(String message) {
        super(message);
    }
}