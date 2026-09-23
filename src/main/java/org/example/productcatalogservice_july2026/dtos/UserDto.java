package org.example.productcatalogservice_july2026.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {
    private Long id;
    private String name;
    private String email;
    //private List<String> roles;
}
