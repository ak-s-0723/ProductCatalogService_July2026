package org.example.productcatalogservice_july2026.TableInheritanceExamples.SingleTable;

import jakarta.persistence.*;

@Entity(name="user_st")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
public class User {
    @Id
    private Long id;
    private String name;
}
