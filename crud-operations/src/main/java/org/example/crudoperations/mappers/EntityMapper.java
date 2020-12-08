package org.example.crudoperations.mappers;

import org.example.crudoperations.entity.Product;
import org.example.crudoperations.entity.User;
import org.example.crudoperations.requests.ProductRequest;
import org.example.crudoperations.requests.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntityMapper {
   Product productRequestToProduct(ProductRequest productRequest);
   User userRequestToUser(UserRequest userRequest);
}
