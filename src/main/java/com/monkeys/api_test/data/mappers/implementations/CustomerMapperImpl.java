package com.monkeys.api_test.data.mappers.implementations;

import com.monkeys.api_test.data.mappers.interfaces.CustomerMapper;
import com.monkeys.api_test.data.models.dto.CustomerDto;
import com.monkeys.api_test.data.models.entities.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setCreatedBy(customer.getCreatedBy());
        customerDto.setLastUpdatedBy(customer.getLastUpdatedBy());
        customerDto.setName(customer.getName());
        customerDto.setSurname(customer.getSurname());
        customerDto.setPhotoUrl(customer.getPhotoUrl());
        return customerDto;
    }

    @Override
    public Customer toEntity(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setCreatedBy(customerDto.getCreatedBy());
        customer.setLastUpdatedBy(customerDto.getLastUpdatedBy());
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setPhotoUrl(customerDto.getPhotoUrl());
        return customer;
    }
}
