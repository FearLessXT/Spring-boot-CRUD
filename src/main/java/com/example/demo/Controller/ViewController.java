package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping("/view-products")
    public String viewProducts() {
        return "view-products";
    }

    @RequestMapping("/add-product")
    public String addProducts() {
        return "add-product";
    }
    @RequestMapping("/update-product")
    public String updateProduct() {
        return "update-product";
    }
}
