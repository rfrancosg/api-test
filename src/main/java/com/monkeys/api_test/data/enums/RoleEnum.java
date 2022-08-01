package com.monkeys.api_test.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ROLE_ADMIN( "ROLE_ADMIN", "Administrator"),
    ROLE_USER( "ROLE_USER", "User");

    private String code;
    private String name;
}
