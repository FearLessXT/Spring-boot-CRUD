package com.example.demo.product;

import com.example.demo.common.BadRequestException;
import com.example.demo.model.Product;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/api/v1/template")
public class ConsumeWebController {

    private final RestTemplate restTemplate = new RestTemplate();

    @RequestMapping(value = "get")
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        //return restTemplate.exchange("http://localhost:8080/products", HttpMethod.GET, entity, String.class).getBody();
        return restTemplate.exchange("http://localhost:8080/api/v1/product/getAll", HttpMethod.GET, entity, String.class).getBody();
    }
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String createProducts(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

        return restTemplate.exchange("http://localhost:8080/api/v1/product/add", HttpMethod.POST, entity, String.class).getBody();
    }
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public String updateProducts(@PathVariable("id") String id, @RequestBody Product product) {
        product.validate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(product, headers);

        return restTemplate.exchange("http://localhost:8080/api/v1/product/update/"+id, HttpMethod.PUT, entity, String.class).getBody();
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteProducts(@PathVariable("id") String id) {
        if(id == null) {
            throw new BadRequestException("ID is Required");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<Product>(headers);

        return restTemplate.exchange("http://localhost:8080/api/v1/product/delete/"+ id, HttpMethod.DELETE, entity, String.class).getBody();
    }
}
