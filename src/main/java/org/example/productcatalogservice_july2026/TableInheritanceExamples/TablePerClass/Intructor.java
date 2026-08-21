package org.example.productcatalogservice_july2026.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;

@Entity(name="instructor_tpc")
public class Intructor extends User {
    private String company;
}
