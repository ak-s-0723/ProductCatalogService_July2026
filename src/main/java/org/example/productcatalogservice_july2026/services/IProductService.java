package org.example.productcatalogservice_july2026.services;

import org.example.productcatalogservice_july2026.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id);

    Product createProduct(Product input);

    List<Product> getAllProducts();
}
