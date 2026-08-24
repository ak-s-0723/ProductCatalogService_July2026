package org.example.productcatalogservice_july2026.repos;

import org.example.productcatalogservice_july2026.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo extends JpaRepository<Category,Long> {

    Optional<Category> findById(Long id);
}
