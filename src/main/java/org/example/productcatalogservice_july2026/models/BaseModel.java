package org.example.productcatalogservice_july2026.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    private Long id;
    private Date createdAt;
    private Date lastUpdatedAt;
    private Status status;
}
