package org.example.productcatalogservice_july2026.controllers;

import org.example.productcatalogservice_july2026.dtos.ProductDto;
import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithValidId_ReturnsProductSuccessfully() {
        //Arrange
        Long id = 5L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone17");
        when(productService.getProductById(id)).thenReturn(product);

        //Act
        ProductDto productDto = productController.getProductById(id);

        //Assert
        assertNotNull(productDto);
        assertEquals(id,productDto.getId());
        assertEquals("Iphone17",productDto.getName());
    }

    @Test
    public void TestGetProductById_WithNegativeId_ResultsInIllegalArgumentException() {
        //Arrange
        Long id = -5L;

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class, ()->productController.getProductById(id));
        assertEquals("Wrong Id",exception.getMessage());
    }

}