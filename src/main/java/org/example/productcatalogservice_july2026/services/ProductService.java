package org.example.productcatalogservice_july2026.services;

import org.example.productcatalogservice_july2026.dtos.FakeStoreProductDto;
import org.example.productcatalogservice_july2026.models.Category;
import org.example.productcatalogservice_july2026.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static java.util.Objects.nonNull;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public Product getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
//        FakeStoreProductDto fakeStoreProductDto =
//                restTemplate.getForObject("https://fakestoreapi.com/products/{id}",
//                        FakeStoreProductDto.class,
//                        id);

//        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
//                restTemplate.getForEntity("https://fakestoreapi.com/products/{id}",
//                        FakeStoreProductDto.class,
//                        id);

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.GET, "https://fakestoreapi.com/products/{id}",null,
                        FakeStoreProductDto.class,
                        id);

        if (fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

       return null;
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

    @Override
    public Product replaceProduct(Long id, Product input) {
        FakeStoreProductDto inputDto = from(input);
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity =
                requestForEntity(HttpMethod.PUT,"https://fakestoreapi.com/products/{id}",
                        inputDto,
                        FakeStoreProductDto.class,
                        id);

        if (fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(200))) {
            return from(fakeStoreProductDtoResponseEntity.getBody());
        }

        return null;
    }


    public <T> ResponseEntity<T> requestForEntity( HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }


    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
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
