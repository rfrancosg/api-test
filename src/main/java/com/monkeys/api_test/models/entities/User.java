package com.monkeys.api_test.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }
}
