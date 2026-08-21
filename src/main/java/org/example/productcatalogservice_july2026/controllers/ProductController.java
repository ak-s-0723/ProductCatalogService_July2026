package org.example.productcatalogservice_july2026.controllers;

import org.example.productcatalogservice_july2026.dtos.CategoryDto;
import org.example.productcatalogservice_july2026.dtos.ProductDto;
import org.example.productcatalogservice_july2026.models.Category;
import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    //@Qualifier("storageProductService")
    private IProductService productService;


//    public ProductController(IProductService productService) {
//        this.productService = productService;
//    }

    @GetMapping("/products")
    public List<ProductDto> getAllProductDetails() {
        List<ProductDto> productDtos = new ArrayList<>();
       List<Product> products = productService.getAllProducts();
       for(Product product : products) {
           ProductDto productDto = from(product);
           productDtos.add(productDto);
       }
       return productDtos;
    }

    //GetProductById
    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long productId) {
        if (productId < 0) {
           throw new IllegalArgumentException("Please pass productId > 0");
        }

        Product product = productService.getProductById(productId);
        if (product != null) {
            return from(product);
        }

        throw new RuntimeException("Product not available");
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        Product input = from(productDto);
        Product output = productService.createProduct(input);
        return from(output);
    }

    @DeleteMapping("/products/{id}")
    public Boolean deleteProductById(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }


    @PutMapping("/products/{id}")
    public ProductDto replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product payload = from(productDto);
        Product output = productService.replaceProduct(id,payload);
        if (output !=null) {
            return from(output);
        }

        throw new RuntimeException("Something went wrong");
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

    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
    }
}
