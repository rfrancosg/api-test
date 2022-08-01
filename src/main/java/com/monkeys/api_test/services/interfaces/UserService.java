package com.monkeys.api_test.services.interfaces;


import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.UserNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserService {

    /**
     * Returns all users
     * @return
     */
    List<UserDto> getUsers();

    /**
     * Creates a user
     * @param userDto
     * @return
     */
    UserDto createUser(UserDto userDto) throws AttributeNotPresentException;

    /**
     * Updates a user
     * @param userDto
     * @return
     */
    UserDto updateUser(UserDto userDto) throws UserNotFoundException, AttributeNotPresentException;

    /**
     * Deletes a user
     * @param id
     */
    void deleteUser(UUID id);
}
