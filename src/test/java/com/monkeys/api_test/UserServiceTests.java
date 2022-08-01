package com.monkeys.api_test;

import com.monkeys.api_test.data.mappers.implementations.UserMapperImpl;
import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.data.models.entities.User;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.UserNotFoundException;
import com.monkeys.api_test.repositories.UserRepository;
import com.monkeys.api_test.services.implementations.UserServiceImpl;
import com.monkeys.api_test.utils.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.*;

@SpringBootTest
class UserServiceTests {

    @Mock
    UserRepository userRepository;

    @Mock
    UserMapperImpl userMapper;

    @Mock
    Utils utils;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void GetAllUsers(){

        Mockito.when(userRepository.findAll()).thenReturn(Arrays.asList(getTestUser()));
        Mockito.when(userMapper.toDto(Mockito.any())).thenReturn(getTestUserDto());

        List<UserDto> userDtos =  userService.getUsers();
        Assert.isTrue(userDtos.stream().findFirst().get().getEmail().equals("email@email.com"));
    }

    @Test
    void UpdateUser() throws UserNotFoundException, AttributeNotPresentException {

        Mockito.when(userRepository.findUserById(Mockito.any())).thenReturn(Optional.of(getTestUser()));
        Mockito.when(userMapper.toDto(Mockito.any())).thenReturn(getTestUserDto());
        Mockito.when(utils.createUUID()).thenReturn(UUID.randomUUID());

        UserDto userDto =  userService.updateUser(getTestUserDto());
        Assert.isTrue(userDto.getEmail().equals("email@email.com"));
    }

    @Test
    void CreateUser() throws AttributeNotPresentException {
        Mockito.when(userMapper.toEntity(Mockito.any())).thenReturn(getTestUser());
        Mockito.when(userMapper.toDto(Mockito.any())).thenReturn(getTestUserDto());
        Mockito.when(utils.createUUID()).thenReturn(UUID.randomUUID());

        UserDto userDto =  userService.createUser(getTestUserDto());
        Assert.isTrue(userDto.getEmail().equals("email@email.com"));
    }

    @Test
    void DeleteUser(){

        userService.deleteUser(UUID.randomUUID());
    }

    private User getTestUser(){
        User user = new User();
        user.setName("name");
        user.setPassword("password");
        user.setSurname("surname");
        user.setEmail("email@email.com");
        user.setId(UUID.randomUUID());

        return user;
    }

    private UserDto getTestUserDto(){
        UserDto userDto = new UserDto();
        userDto.setName("name");
        userDto.setPassword("password");
        userDto.setSurname("surname");
        userDto.setEmail("email@email.com");
        userDto.setId(UUID.randomUUID());

        return userDto;
    }
}
