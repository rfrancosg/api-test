package com.monkeys.api_test.controllers;

import com.monkeys.api_test.data.models.dto.LoginDto;
import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.services.interfaces.LoginService;
import com.monkeys.api_test.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.LOGIN_PATH)
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "", produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public UserDto login(@RequestBody LoginDto userDto) throws Exception {
        return loginService.login(userDto);
    }
}
