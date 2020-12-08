package org.example.crudoperations.integratedtests;

import lombok.extern.slf4j.Slf4j;
import org.example.crudoperations.CrudOperationsApplication;
import org.example.crudoperations.entity.Product;
import org.example.crudoperations.exceptions.ProductNotExist;
import org.example.crudoperations.repository.ProductRepository;
import org.example.crudoperations.response.ExceptionResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudOperationsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate=new TestRestTemplate();

    HttpHeaders headers=new HttpHeaders();

    @Autowired
    ProductRepository productRepository;

    static UUID id;
    Product product=new Product("product1", 199, Double.valueOf(440));



    @Test
    public void testCreateProduct() {

        HttpEntity<Product> entity = new HttpEntity<>(product, headers);
        ResponseEntity<Product> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/addProduct"),
                HttpMethod.POST, entity, Product.class);

        Assert.assertNotNull(response.getBody());

        Assert.assertEquals(product.getName(), response.getBody().getName());
        Assert.assertEquals(product.getPrice(), response.getBody().getPrice());
        Assert.assertEquals(product.getQuantity(), response.getBody().getQuantity());


        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        id= response.getBody().getId();
    }
    @Test
    public void testRetrieveProduct()  {
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<Product> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/product/"+id),
                HttpMethod.GET, entity, Product.class);

        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(product.getName(), response.getBody().getName());
        Assert.assertEquals(product.getPrice(), response.getBody().getPrice());
        Assert.assertEquals(product.getQuantity(), response.getBody().getQuantity());

    }
    @Test
    public void testRetrieveProductNotFound()  {
        UUID id=UUID.randomUUID();
        ProductNotExist productNotExistException=new ProductNotExist(id);

        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<ExceptionResponse> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/product/"+id),
                HttpMethod.GET, entity, ExceptionResponse.class);

        Assert.assertNotNull(response.getBody());
        Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assert.assertEquals(productNotExistException.getMessage(), response.getBody().getMessage());

    }

    @Test
    public void testToDeleteProduct()  {

        Product product=productRepository.findAll().get(0);

        HttpEntity<Product> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/delete/"+product.getId()),
                HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testGetAllProducts()  {

        HttpEntity<Product> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/products"),
                HttpMethod.GET, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testToUpdateProduct()  {

        product.setQuantity(200);
        product.setId(id);
        HttpEntity<Product> entity = new HttpEntity<>(product, headers);
        ResponseEntity<Product> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/update/"+id),
                HttpMethod.PUT, entity, Product.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(product.getName(), response.getBody().getName());
        Assert.assertEquals(product.getPrice(), response.getBody().getPrice());
        Assert.assertEquals(product.getQuantity(), response.getBody().getQuantity());


    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}