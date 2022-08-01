package com.monkeys.api_test.data.models.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private boolean isAdmin;
    private String token;
}
