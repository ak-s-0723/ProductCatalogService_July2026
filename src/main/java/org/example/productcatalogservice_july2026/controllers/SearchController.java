package org.example.productcatalogservice_july2026.controllers;

import org.example.productcatalogservice_july2026.dtos.SearchRequestDto;
import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public Page<Product> searchProducts(@RequestBody SearchRequestDto searchRequestDto) {
        return searchService.searchProducts(searchRequestDto.getQueryString(),
                searchRequestDto.getPageSize(),
                searchRequestDto.getPageNumber());
    }
}
