package org.example.crudoperations.service;


import org.example.crudoperations.entity.Product;
import org.example.crudoperations.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService;
    @Mock
    private ProductRepository repository;

    @Captor
    ArgumentCaptor<Product> productArgumentCaptor;

    Product testProduct;

    UUID id=UUID.randomUUID();
    @Before
    public void setup(){
        testProduct=new Product("prod1",20,1200.0);

    }
    @Test
    public void testSaveProduct() {
        productService.saveProduct(testProduct);
        Mockito.verify(repository,Mockito.times(1)).save(productArgumentCaptor.capture());
        Assert.assertEquals(productArgumentCaptor.getValue(),testProduct);

    }
    @Test
    public void testSaveProducts() {

        productService.saveProducts(Arrays.asList(testProduct));
        Mockito.verify(repository,Mockito.times(1)).saveAll(Arrays.asList(testProduct));

    }
    @Test
    public void testGetProducts() {
        List productList=Arrays.asList(testProduct);
        Mockito.when(repository.findAll()).thenReturn(productList);
        List returnedList= productService.getProducts();
        Mockito.verify(repository,Mockito.times(1)).findAll();
        Assert.assertNotNull(returnedList);
        Assert.assertEquals(productList,returnedList);

    }
    @Test
    public void testGetProductById() {

        Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.of(testProduct));
        Product returnedProduct= productService.getProductById(id);
        Mockito.verify(repository,Mockito.times(1)).findById(id);
        Assert.assertNotNull(returnedProduct);
        Assert.assertEquals(testProduct,returnedProduct);

    }
    @Test
    public void testGetProductNullById() {
        Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.empty());
        Product returnedProduct= productService.getProductById(id);
        Mockito.verify(repository,Mockito.times(1)).findById(id);
        Assert.assertNull(returnedProduct);


    }
    @Test
    public void testDeleteProductById() {
         productService.deleteProduct(id);
        Mockito.verify(repository,Mockito.times(1)).deleteById(id);


    }
    @Test
    public void testUpdateProduct() {
        Product updatedProduct =new Product("prod1",100,1200.0);
        Mockito.when(repository.findById(Mockito.eq(id))).thenReturn(Optional.of(testProduct));
        Mockito.when(repository.save(Mockito.eq(updatedProduct))).thenReturn(updatedProduct);

        Product returnedProduct= productService.updateProduct(id,updatedProduct);
        Mockito.verify(repository,Mockito.times(1)).findById(id);
        Mockito.verify(repository,Mockito.times(1)).save(updatedProduct);
        Assert.assertNotNull(returnedProduct);
        Assert.assertEquals(Integer.valueOf(100),returnedProduct.getQuantity());

    }
}

