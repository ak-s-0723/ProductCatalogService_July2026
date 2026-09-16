package org.example.productcatalogservice_july2026.services;

import org.example.productcatalogservice_july2026.models.Product;
import org.example.productcatalogservice_july2026.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    private ProductRepo productRepo;

    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber) {
        Sort sortByPrice = Sort.by("price"); //increasing
        Sort sortByIdDesc = Sort.by("id").descending();
        Sort sort = sortByPrice.and(sortByIdDesc);
      return productRepo.findByName(query, PageRequest.of(pageNumber,pageSize, sort));
    }
}


/*

{
	"queryString" : "laptop",
	"pageSize" : 5,
	"pageNumber" : 0,
	"sortParams" : [
		{
		"sortParam" : "price",
		"sortType" : "ASC"
	  },{
		"sortParam" : "id",
		"sortType" :  "DESC"
	}
	]
}

 */