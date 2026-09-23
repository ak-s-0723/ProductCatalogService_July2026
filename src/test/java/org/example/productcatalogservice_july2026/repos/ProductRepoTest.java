package org.example.productcatalogservice_july2026.repos;

import org.example.productcatalogservice_july2026.models.Category;
import org.example.productcatalogservice_july2026.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepoTest {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    //@Test
    public void addDataToRDS() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Teddy Bear");
        product.setPrice(500D);

        Product product2 = new Product();
        product2.setId(10L);
        product2.setName("Mickey Mouse");
        product2.setPrice(800D);

        Category category = new Category();
        category.setId(1L);
        category.setName("Toys");
        categoryRepo.save(category);

        product.setCategory(category);
        product2.setCategory(category);

        productRepo.save(product);
        productRepo.save(product2);
    }

}
