package com.monkeys.api_test.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Utils {


    public UUID createUUID(){
        return UUID.randomUUID();
    }

    public String generateEncryptedPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }
    public boolean checkPassword(String password, String hashed) {
        return BCrypt.checkpw(password, hashed);
    }
}
