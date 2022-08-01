package com.monkeys.api_test.services.interfaces;

import com.monkeys.api_test.data.models.dto.LoginDto;
import com.monkeys.api_test.data.models.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface LoginService extends UserDetailsService {

    /**
     * Login
     * @param loginDto
     * @return
     * @throws Exception
     */
    UserDto login(LoginDto loginDto) throws Exception;
}
