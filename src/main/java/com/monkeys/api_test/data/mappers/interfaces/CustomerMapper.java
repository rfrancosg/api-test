package com.monkeys.api_test.data.mappers.interfaces;

import com.monkeys.api_test.data.models.dto.CustomerDto;
import com.monkeys.api_test.data.models.entities.Customer;

public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto customer);
}
