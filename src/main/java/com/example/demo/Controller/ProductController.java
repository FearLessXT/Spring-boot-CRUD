package com.example.demo.Controller;

import com.example.demo.SimpleFilter;
import com.example.demo.common.BadRequestException;
import com.example.demo.exception.ProductionNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    @Autowired
    SimpleFilter simpleFilter;

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/getAll")
    public ResponseEntity<Object> getProductId(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        request.getRemoteAddr();
        request.getRemoteHost();

        Collection<Product> productList = productService.getProduct();

        if (productList.size() == 0) {
            throw new ProductionNotFoundException();
        }

        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> getProduct(@RequestBody Product product) {

        product.validate();
        product.setDate(LocalDateTime.now());
        productService.createProduct(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        product.validate();

        try {
            Product exitingProduct = productService.findById(id);

            exitingProduct.setName(product.getName());
            exitingProduct.setCategory(product.getCategory());
            exitingProduct.setDate(LocalDateTime.now());
            productService.updateProduct(id, product);

            return new ResponseEntity<>(exitingProduct, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("ID Not Found!");
            throw new ProductionNotFoundException();
        }
//        if(!productRepo.containsKey(id)) throw new ProductionNotFoundException();
//        productRepo.remove(id);
//        product.setId(id);
//        productRepo.put(id, product);
//
//        return new ResponseEntity<>("Product is updated Successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        if (id == null) {
            throw new BadRequestException("ID is Required");
        }
        try {
            Product productId = productService.findById(id);
            if (productId == null) {
                throw new BadRequestException("No ID Found");
            }
            productService.deleteProduct(id);

            return new ResponseEntity<>(productId, HttpStatus.OK);
        } catch (Exception e) {
            throw new ProductionNotFoundException();
        }
    }
}
