package org.example.productcatalogservice_july2026.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="ta_st")
@DiscriminatorValue(value="1")
public class Ta extends User {
    private Integer helpRequests;
}
