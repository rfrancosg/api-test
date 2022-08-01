package com.monkeys.api_test.controllers;

import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.UserNotFoundException;
import com.monkeys.api_test.services.interfaces.UserService;
import com.monkeys.api_test.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constants.USER_PATH)
public class UserController {

    @Autowired
    UserService adminService;

    @GetMapping(value = Constants.ALL_PATH, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<UserDto> getAllUsers() {
        return adminService.getUsers();
    }

    @PostMapping(value = Constants.CREATE_PATH, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto createUser(@RequestBody UserDto userDto) throws AttributeNotPresentException {
        return adminService.createUser(userDto);
    }

    @PutMapping(value = Constants.UPDATE_PATH, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public UserDto updateUser(@RequestBody UserDto userDto) throws UserNotFoundException, AttributeNotPresentException {
        return adminService.updateUser(userDto);
    }

    @DeleteMapping(value = Constants.USER_ID_PARAM, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteUser(@PathVariable(Constants.USER_ID_PATH_PARAM) UUID userId) {
        adminService.deleteUser(userId);
    }
}
