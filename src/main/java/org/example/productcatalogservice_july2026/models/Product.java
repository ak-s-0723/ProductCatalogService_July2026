package org.example.productcatalogservice_july2026.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product extends BaseModel {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private Category category;
    private Boolean isPrimeSaleEligible;   // This is a need why we created ProductDto
}
