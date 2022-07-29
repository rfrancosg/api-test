package com.monkeys.api_test.services.implementations;

import com.monkeys.api_test.repositories.UserRepository;
import com.monkeys.api_test.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;


}
