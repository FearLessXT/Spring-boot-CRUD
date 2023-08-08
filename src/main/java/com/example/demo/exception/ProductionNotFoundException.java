package com.example.demo.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ProductionNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
}
