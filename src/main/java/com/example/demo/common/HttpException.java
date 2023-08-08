package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HttpException extends RuntimeException{
    protected String message;
    private String errorCode;
    protected HttpStatus httpStatus;

    public HttpException(String message, String errorCode, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }
}