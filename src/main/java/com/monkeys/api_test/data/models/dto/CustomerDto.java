package com.monkeys.api_test.data.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDto {

    private UUID id;
    private String name;
    private String surname;
    private String photoUrl;
    private UUID createdBy;
    private UUID lastUpdatedBy;
}
