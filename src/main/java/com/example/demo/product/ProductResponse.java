package com.example.demo.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductResponse {
    private String status;
    private String message;
    private Product product;
}
