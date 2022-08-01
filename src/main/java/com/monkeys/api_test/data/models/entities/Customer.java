package com.monkeys.api_test.data.models.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    private UUID id;
    private String name;
    private String surname;
    private String photoUrl;
    private UUID createdBy;
    private UUID lastUpdatedBy;
}
