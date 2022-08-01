package com.monkeys.api_test.services.interfaces;

import com.monkeys.api_test.data.models.dto.LoginDto;
import com.monkeys.api_test.data.models.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginService extends UserDetailsService {

    UserDto login(LoginDto loginDto) throws Exception;
}
