package com.example.demo.product;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class productService {
    private static Map<String, Product> productRepo = new HashMap<>();
    static {
        Product honey = new Product();
        honey.setId(honey.getId());
        honey.setName(honey.getName());
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId(honey.getId());
        almond.setName(honey.getName());
        productRepo.put(almond.getId(), almond);
    }
}
