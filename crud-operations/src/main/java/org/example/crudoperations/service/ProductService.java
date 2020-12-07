package org.example.crudoperations.service;

import org.example.crudoperations.entity.Product;
import org.example.crudoperations.exceptions.ProductNotExist;
import org.example.crudoperations.repository.ProductRepository;
import org.example.crudoperations.requests.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
       return productRepository.save(product);
    }

    public List<Product> getProducts(){
       return productRepository.findAll();

    }
    public Product getProductById(UUID id){
        return productRepository.findById(id).orElseThrow(()->new ProductNotExist(id));
    }

    public void deleteProduct(UUID id){
         productRepository.deleteById(id);
    }

    public Product updateProduct(UUID id, ProductRequest product){
        Optional<Product> productToUpdateOptional = productRepository.findById(id);

        if (productToUpdateOptional.isPresent()) {
            Product productToUpdate = productToUpdateOptional.get();
            productToUpdate.setQuantity(product.getQuantity());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setName(product.getName());

            return productRepository.save(productToUpdate);
        } else {
         throw new ProductNotExist(id);
        }

    }
}
