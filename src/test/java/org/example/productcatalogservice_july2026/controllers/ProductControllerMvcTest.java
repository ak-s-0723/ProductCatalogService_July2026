package org.example.productcatalogservice_july2026.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatalogservice_july2026.dtos.ProductDto;
import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetAllProductsAPI_RunSuccessfully() throws Exception {
        //Arrange
        Product product = new Product();
        product.setId(2L);
        product.setName("Macbook");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        when(productService.getAllProducts()).thenReturn(productList);


        //string representation of json of list of productDto
        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("Macbook");
        List<ProductDto> productDtos = new ArrayList<>();
        productDtos.add(productDto);
        String expectedResponse = objectMapper.writeValueAsString(productDtos);
        System.out.println(expectedResponse);


        //Act and Assert
        mockMvc.perform(get("/products"))    //Act
                .andExpect(status().isOk())          //Assert
                .andExpect(content().string(expectedResponse));     //Assert

    }


}
