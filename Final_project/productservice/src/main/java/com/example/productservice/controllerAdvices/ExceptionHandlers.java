package com.example.productservice.controllerAdvices;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Void> handleArithmaticException(){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
