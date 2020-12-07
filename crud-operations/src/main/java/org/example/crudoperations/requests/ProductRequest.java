package org.example.crudoperations.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ProductRequest {
    private UUID id;
    private String name;
    private Integer quantity;
    private Double price;
}
