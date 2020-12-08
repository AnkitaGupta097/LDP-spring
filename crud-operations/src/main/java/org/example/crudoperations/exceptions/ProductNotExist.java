package org.example.crudoperations.exceptions;

import java.util.UUID;

public class ProductNotExist extends RuntimeException{
   public ProductNotExist(UUID id){
        super("Product not exist for id "+id);
    }
}
