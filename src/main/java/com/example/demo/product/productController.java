package com.example.demo.product;

import com.example.demo.common.BadRequestException;
import com.example.demo.exception.ProductionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
public class productController {
    private final static Map<String, Product> productRepo = new HashMap<>();

    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Beer");
        honey.setCategory("Drink");
        honey.setDate(LocalDateTime.now());
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Milk");
        almond.setCategory("Milk");
        almond.setDate(LocalDateTime.now());
        productRepo.put(almond.getId(), almond);
    }

    @RequestMapping(value = "/getAll")
    public ResponseEntity<Object> getProductId() {
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
