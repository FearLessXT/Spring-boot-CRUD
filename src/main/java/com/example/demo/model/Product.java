package com.example.demo.model;

import com.example.demo.common.BadRequestException;
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
    public void validate() {
        if(id == null) {
            throw new BadRequestException("ID is Required");
        }
        if(name == null) {
            throw new BadRequestException("Name is Required");
        }
        if(category == null) {
            throw new BadRequestException("Category is Required");
        }
    }
}
