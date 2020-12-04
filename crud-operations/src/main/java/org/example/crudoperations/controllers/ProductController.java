package org.example.crudoperations.controllers;

import org.example.crudoperations.entity.Product;
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

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
       return productService.saveProduct(product);
    }
    @PostMapping("/addProducts")
    public List<Product> addProduct(@RequestBody List<Product> products){
        return productService.saveProducts(products);
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
    public Product updateTutorial(@PathVariable("id") UUID id, @RequestBody Product product) {
       return productService.updateProduct(id,product);
    }



}
