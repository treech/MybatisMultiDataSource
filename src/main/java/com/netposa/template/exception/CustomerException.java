package com.netposa.template.exception;

import lombok.Data;

/**
 * Author:LP
 */
@Data
public class CustomerException extends RuntimeException {

    private int code;
    private String message;
    private Throwable error;

    public CustomerException() {
    }

    public CustomerException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public CustomerException(String message) {
        this.code = 500;
        this.message = message;
    }

    public CustomerException(int code, String message, Throwable error) {
        this.code = code;
        this.message = message;
        this.error = error;
    }

}
