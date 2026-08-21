package org.example.productcatalogservice_july2026.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="ta_tpc")
public class Ta extends User {
    private Integer helpRequests;
}
