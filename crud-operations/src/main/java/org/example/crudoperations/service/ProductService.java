package org.example.crudoperations.service;

import org.example.crudoperations.entity.Product;
import org.example.crudoperations.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
       return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }
    public List<Product> getProducts(){
       return repository.findAll();

    }
    public Product getProductById(UUID id){
        return repository.findById(id).orElse(null);
    }

    public void deleteProduct(UUID id){
         repository.deleteById(id);
    }

    public Product updateProduct(UUID id,Product product){
        Optional<Product> productToUpdateOptional = repository.findById(id);

        if (productToUpdateOptional.isPresent()) {
            Product productToUpdate = productToUpdateOptional.get();
            productToUpdate.setQuantity(product.getQuantity());
            productToUpdate.setPrice(product.getPrice());
            productToUpdate.setName(product.getName());

            return repository.save(productToUpdate);
        } else {
            return null;
        }
    }
}
