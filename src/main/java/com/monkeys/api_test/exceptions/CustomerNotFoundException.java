package com.monkeys.api_test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Attibutes missing")
public class CustomerNotFoundException extends Exception{

    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
