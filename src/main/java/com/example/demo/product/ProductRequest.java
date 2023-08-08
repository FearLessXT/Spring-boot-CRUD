package com.example.demo.product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProductRequest {
    private String id;
    private String name;
    private String category;
    private LocalDateTime date;
}
