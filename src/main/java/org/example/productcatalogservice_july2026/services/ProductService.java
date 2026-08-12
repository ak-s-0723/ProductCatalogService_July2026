package org.example.productcatalogservice_july2026.services;

import org.example.productcatalogservice_july2026.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_july2026.models.Category;
import org.example.productcatalogservice_july2026.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/{id}",
                        FakeStoreProductDto.class,
                        id);
        return from(fakeStoreProductDto);
    }

    @Override
    public Product createProduct(Product input) {
        return null;
    }

    //ToDo for students
    @Override
    public List<Product> getAllProducts() {
        return null;
    }


    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return  product;
    }
}
