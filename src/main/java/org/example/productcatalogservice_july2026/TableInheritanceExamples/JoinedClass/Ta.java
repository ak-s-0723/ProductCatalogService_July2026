package org.example.productcatalogservice_july2026.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="ta_jc")
@PrimaryKeyJoinColumn(name="user_id")
public class Ta extends User {
    private Integer helpRequests;
}
