package org.example.productcatalogservice_july2026.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name="instructor_st")
@DiscriminatorValue(value="2")
public class Intructor extends User {
    private String company;
}
