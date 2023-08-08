package com.example.demo.product;

import com.example.demo.SimpleFilter;
import com.example.demo.common.BadRequestException;
import com.example.demo.exception.ProductionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/v1/product")
public class productController {

    @Autowired
    SimpleFilter simpleFilter;
    private final static Map<String, Product> productRepo = new HashMap<>();

    @RequestMapping(value = "/getAll")
    public ResponseEntity<Object> getProductId(ServletRequest request, ServletResponse response) throws ServletException, IOException {

        request.getRemoteAddr();
        request.getRemoteHost();
        simpleFilter.doFilter(request, response);

        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> getProduct(@RequestBody Product product){
        product.validate();

        productRepo.put(product.getId(), product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@RequestBody Product product) {
        product.validate();

        try {
            Product exitingProduct = productRepo.get(product.getId());
            exitingProduct.setName(product.getName());
            exitingProduct.setCategory(product.getCategory());
            productRepo.put(product.getId(), exitingProduct);

            return new ResponseEntity<>(exitingProduct, HttpStatus.OK);
        }
        catch (Exception e) {
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
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteProduct(@RequestBody Product product) {
        if(product.getId() == null) {
            throw new BadRequestException("ID is Required");
        }
        Product productId = productRepo.get(product.getId());
        productRepo.remove(product.getId(), productId);

        return new ResponseEntity<>(productId, HttpStatus.OK);
    }
}
