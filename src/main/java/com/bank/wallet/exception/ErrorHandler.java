package com.bank.wallet.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.context.support.DefaultMessageSourceResolvable;


import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundException(NotFoundException exception) {
        Map<String, String> result = Map.of("Not Found Error", exception.getMessage());
        log.warn(String.valueOf(result), exception, exception.getMessage());
        return result;
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public Map<String, String> handleTooBigException(TooBigAmount exception) {
        Map<String, String> result = Map.of("Not enough money", exception.getMessage());
        log.warn(String.valueOf(result), exception, exception.getMessage());
        return result;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleUnknownRequestException(UnknownRequestException exception) {
        Map<String, String> result = Map.of("Unknown request", exception.getMessage());
        log.warn(String.valueOf(result), exception, exception.getMessage());
        return result;
    }


    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Set<String>> handleNotValidException(MethodArgumentNotValidException exception) {
        Set<String> errors = exception.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
        Map<String, Set<String>> result = Map.of("Not valid request", errors);
        log.warn(String.valueOf(result), exception, exception.getMessage());
        return result;
    }
}
