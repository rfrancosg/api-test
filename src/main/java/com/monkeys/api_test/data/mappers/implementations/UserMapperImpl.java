package com.monkeys.api_test.data.mappers.implementations;

import com.monkeys.api_test.data.mappers.interfaces.UserMapper;
import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.data.models.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setAdmin(user.isAdmin());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());

        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setAdmin(userDto.isAdmin());
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());

        return user;
    }
}
