package org.example.productcatalogservice_july2026.repos;

import org.example.productcatalogservice_july2026.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepo extends JpaRepository<Product,Long> {
    Optional<Product> findById(Long id);

    List<Product> findAll();


    Product save(Product product);

    void deleteById(Long id);
}