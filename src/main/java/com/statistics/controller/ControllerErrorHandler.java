package com.statistics.controller;

import com.statistics.exception.NoTransactionsMadeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleInvalidInput(MethodArgumentNotValidException exception ) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRunTimeException( final RuntimeException exception ) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoTransactionsMadeException.class)
    public ResponseEntity handleNoTransactionMadeException( final NoTransactionsMadeException exception ) {
        return new ResponseEntity<>(exception.getLocalizedMessage(), HttpStatus.NO_CONTENT);
    }


}
