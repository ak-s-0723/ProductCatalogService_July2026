package org.example.productcatalogservice_july2026.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name="instructor_jc")
@PrimaryKeyJoinColumn(name="user_id")
public class Intructor extends User {
    private String company;
}
