package org.example.productcatalogservice_july2026.repos;

import org.example.productcatalogservice_july2026.models.Category;
import org.example.productcatalogservice_july2026.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;


    @Test
    @Transactional
    public void testFetchTypes() {
        Optional<Category> categoryOptional = categoryRepo.findById(1L);
        Category category = categoryOptional.get();
        for(Product product :category.getProducts()) {
            System.out.println(product.getName());
        }
    }
}


//        SELECT product_id, product_name, price
//FROM products
//WHERE category_id IN (
//        SELECT category_id
//FROM categories
//        WHERE category_name = 'Electronics'
//);


//Hot seller category
//
//1. Laptop (cat_id) -> macbook air, samsung tab
//2. Phone (cat_id) -> iphone17, iphone 17 pro, ...
//3. Tshirts