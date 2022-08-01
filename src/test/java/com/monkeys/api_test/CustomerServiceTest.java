package com.monkeys.api_test;

import com.monkeys.api_test.data.mappers.implementations.CustomerMapperImpl;
import com.monkeys.api_test.data.models.dto.CustomerDto;
import com.monkeys.api_test.data.models.entities.Customer;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.CustomerNotFoundException;
import com.monkeys.api_test.repositories.CustomerRepository;
import com.monkeys.api_test.services.implementations.CustomerServiceImpl;
import com.monkeys.api_test.utils.Utils;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    CustomerMapperImpl customerMapper;

    @Mock
    Utils utils;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    void GetAllCustomers(){

        Mockito.when(customerRepository.findAll()).thenReturn(Arrays.asList(getTestCustomer()));
        Mockito.when(customerMapper.toDto(Mockito.any())).thenReturn(getTestCustomerDto());

        List<CustomerDto> customerDtos =  customerService.getCustomers();
        Assert.isTrue(customerDtos.stream().findFirst().get().getName().equals("name"));
    }

    @Test
    void GetCustomerById() throws CustomerNotFoundException {

        Mockito.when(customerRepository.findCustomerById(Mockito.any())).thenReturn(Optional.of(getTestCustomer()));
        Mockito.when(customerMapper.toDto(Mockito.any())).thenReturn(getTestCustomerDto());

        CustomerDto customerDto =  customerService.getCustomerById(UUID.randomUUID());
        Assert.isTrue(customerDto.getName().equals("name"));
    }

    @Test
    void UpdateCustomer() throws AttributeNotPresentException, CustomerNotFoundException {

        Mockito.when(customerRepository.findCustomerById(Mockito.any())).thenReturn(Optional.of(getTestCustomer()));
        Mockito.when(customerMapper.toDto(Mockito.any())).thenReturn(getTestCustomerDto());
        Mockito.when(utils.createUUID()).thenReturn(UUID.randomUUID());

        CustomerDto customerDto =  customerService.updateCustomer(getTestCustomerDto());
        Assert.isTrue(customerDto.getName().equals("name"));
    }

    @Test
    void CreateCustomer() throws AttributeNotPresentException {
        Mockito.when(customerMapper.toEntity(Mockito.any())).thenReturn(getTestCustomer());
        Mockito.when(utils.createUUID()).thenReturn(UUID.randomUUID());

        CustomerDto customerDto =  customerService.createCustomer(getTestCustomerDto());
        Assert.isTrue(customerDto.getName().equals("name"));
    }

    @Test
    void DeleteCustomer(){

        customerService.deleteCustomer(UUID.randomUUID());
    }

    private Customer getTestCustomer(){
        Customer customer = new Customer();
        customer.setName("name");
        customer.setSurname("surname");
        customer.setId(UUID.randomUUID());

        return customer;
    }

    private CustomerDto getTestCustomerDto(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("name");
        customerDto.setSurname("surname");
        customerDto.setId(UUID.randomUUID());

        return customerDto;
    }
}
