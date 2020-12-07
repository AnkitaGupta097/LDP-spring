package org.example.crudoperations.integratedtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.crudoperations.CrudOperationsApplication;
import org.example.crudoperations.entity.Product;
import org.example.crudoperations.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudOperationsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate=new TestRestTemplate();

    HttpHeaders headers=new HttpHeaders();
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    ProductRepository productRepository;

    UUID id=UUID.randomUUID();
    @Test
    public void testRetrieveProduct() throws JsonProcessingException {


        Product product = new Product( "product1", 199, Double.valueOf(440));
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        Mockito.when(productRepository.findById(Mockito.eq(id))).thenReturn(Optional.of(product));
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/product/"+id),
                HttpMethod.GET, entity, String.class);

        String expected = objectMapper.writeValueAsString(product);

        Assert.assertEquals(expected, response.getBody());
    }
    @Test
    public void testCreateProduct() throws JsonProcessingException {


        Product product = new Product("product1", 199, Double.valueOf(440));

        HttpEntity<Product> entity = new HttpEntity<>(product, headers);
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);
        ResponseEntity<Product> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/addProduct"),
                HttpMethod.POST, entity, Product.class);


        Assert.assertEquals(product.getName(), response.getBody().getName());
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
    @Test
    public void testDeleteProduct() throws JsonProcessingException {


        HttpEntity<Product> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin","admin").exchange(
                createURLWithPort("/delete/"+id),
                HttpMethod.DELETE, entity, String.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());

    }


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}