package com.example.demo.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Product {
    private String id;
    private String name;
    private String category;
    private LocalDateTime date;
}
