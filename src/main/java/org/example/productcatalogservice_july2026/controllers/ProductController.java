package org.example.productcatalogservice_july2026.controllers;

import org.example.productcatalogservice_july2026.models.Product;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @GetMapping("/products")
    public Product getProductDetails() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Iphone");
        product.setPrice(120000D);
        return product;
    }
}
