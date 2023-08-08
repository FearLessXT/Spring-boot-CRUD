package com.example.demo.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class BadRequestException extends HttpException {
    public BadRequestException(String message){
        this(message, null);
    }
    public BadRequestException(String message, String errorCode) {
        super(message, errorCode, HttpStatus.BAD_REQUEST);
    }
}