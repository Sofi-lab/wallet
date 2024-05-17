package com.bank.wallet.exception;

public class TooBigAmount extends RuntimeException {

    public TooBigAmount(String message) {
        super(message);
    }
}

