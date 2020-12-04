package org.example.crudoperations.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
public class Product extends BaseEntity {

    private String name;
    private Integer quantity;
    private Double price;

}
