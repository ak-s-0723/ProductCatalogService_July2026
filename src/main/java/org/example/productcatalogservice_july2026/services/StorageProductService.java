package org.example.productcatalogservice_july2026.services;

import org.example.productcatalogservice_july2026.dtos.UserDto;
import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.models.Status;
import org.example.productcatalogservice_july2026.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public Product getProductDetailsBasedOnUserRole(Long productId, Long userId) {
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isEmpty()) return null;

        //call user service and get user details using userId
        ResponseEntity<UserDto> userDtoResponseEntity =
                restTemplate.getForEntity("http://userservice/users/{userId}", UserDto.class, userId);

        //If we get valid  (not-null) userDto, then we were able to call User service successfully and we should return product details
        if (userDtoResponseEntity.getBody() !=null) {
            return optionalProduct.get();
        }

        return null;

    }
}
