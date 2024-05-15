package com.bank.wallet.exception;

public class UnknownRequestException extends RuntimeException {
    public UnknownRequestException(String message) {
        super(message);
    }
}
