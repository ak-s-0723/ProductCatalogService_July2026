package org.example.productcatalogservice_july2026.controllers;

import org.example.productcatalogservice_july2026.dtos.CategoryDto;
import org.example.productcatalogservice_july2026.dtos.ProductDto;
import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;

//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/products")
    public List<ProductDto> getAllProductDetails() {
       return null;
    }

    //GetProductById
    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId) {
        Product product = productService.getProductById(productId);
        return from(product);
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return null;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }
}
