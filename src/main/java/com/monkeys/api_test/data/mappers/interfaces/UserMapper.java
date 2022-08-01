package com.monkeys.api_test.data.mappers.interfaces;

import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.data.models.entities.User;

public interface UserMapper {
    UserDto toDto (User user);
    User toEntity (UserDto user);
}
