package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ProductionExceptionController {
    @ExceptionHandler(value = ProductionNotFoundException.class)
    public ResponseEntity<Object> exception(ProductionNotFoundException exception) {
        return new ResponseEntity<>("Product Not Found", HttpStatus.NOT_FOUND);
    }
}
