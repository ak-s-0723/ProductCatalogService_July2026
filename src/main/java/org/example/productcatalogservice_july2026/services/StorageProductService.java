package org.example.productcatalogservice_july2026.services;

import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.models.Status;
import org.example.productcatalogservice_july2026.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if (productOptional.isEmpty()) {
            return null;
        }

        return productOptional.get();
    }

    @Override
    public Product createProduct(Product input) {
        Optional<Product> optionalProduct = productRepo.findById(input.getId());
        if (optionalProduct.isPresent()) {
           throw new RuntimeException("Product already exist with id "+input.getId());
        }

       return productRepo.save(input);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //ToDo for Students
    @Override
    public Product replaceProduct(Long id, Product input) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isPresent()) {
            Product product = productOptional.get();
            if (product.getStatus().equals(Status.ACTIVE)) {
                product.setStatus(Status.INACTIVE);
                product.setLastUpdatedAt(new Date());
                productRepo.save(product);
            } else {
                productRepo.deleteById(id);
            }
            return true;
        }

        return false;
    }
}
