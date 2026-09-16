package org.example.productcatalogservice_july2026.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchRequestDto {
    private Integer pageNumber;
    private Integer pageSize;
    private String queryString;
}
