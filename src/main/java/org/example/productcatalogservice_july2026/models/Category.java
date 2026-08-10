package org.example.productcatalogservice_july2026.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category extends BaseModel {
    private String name;
    private String description;
    private List<Product> products;
}
