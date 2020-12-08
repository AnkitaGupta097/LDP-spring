package org.example.crudoperations.controllers;

import org.example.crudoperations.entity.Product;
import org.example.crudoperations.mappers.EntityMapper;
import org.example.crudoperations.requests.ProductRequest;
import org.example.crudoperations.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins ="${client.url}")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private EntityMapper entityMapper;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody ProductRequest product){
       return productService.saveProduct(entityMapper.productRequestToProduct(product));
    }

    @GetMapping("/products")
    public List<Product> findAllProducts(){
        return productService.getProducts();
    }
    @GetMapping("/product/{id}")
    public Product findProductById(@PathVariable UUID id){
        return productService.getProductById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable UUID id){
         productService.deleteProduct(id);
    }
    @PutMapping("/update/{id}")
    public Product updateTutorial(@PathVariable("id") UUID id, @RequestBody ProductRequest product) {
       return productService.updateProduct(id,product);
    }



}
