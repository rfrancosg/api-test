package com.monkeys.api_test.services.implementations;

import com.monkeys.api_test.data.mappers.implementations.CustomerMapperImpl;
import com.monkeys.api_test.data.models.dto.CustomerDto;
import com.monkeys.api_test.data.models.entities.Customer;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.CustomerNotFoundException;
import com.monkeys.api_test.repositories.CustomerRepository;
import com.monkeys.api_test.services.interfaces.CustomerService;
import com.monkeys.api_test.utils.Constants;
import com.monkeys.api_test.utils.S3Util;
import com.monkeys.api_test.utils.Utils;
import com.monkeys.api_test.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapperImpl customerMapper;

    @Autowired
    Utils utils;

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(x -> customerMapper.toDto(x)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(UUID id) throws CustomerNotFoundException {
        Optional<Customer> maybeCustomer = customerRepository.findCustomerById(id);
            if (maybeCustomer.isPresent()){
                Customer customer = maybeCustomer.get();

                return customerMapper.toDto(customer);
        }
        throw new CustomerNotFoundException("Customer not fournd");
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) throws AttributeNotPresentException {
        ValidateCustomer(customerDto);
        customerDto.setId(utils.createUUID());
        Customer userToCreate = customerMapper.toEntity(customerDto);
        customerRepository.saveAndFlush(userToCreate);
        return customerDto;

    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) throws AttributeNotPresentException, CustomerNotFoundException {
        ValidateCustomer(customerDto);
        Optional<Customer> maybeCustomerToUpdate = customerRepository.findCustomerById(customerDto.getId());
        if (maybeCustomerToUpdate.isPresent()){
            Customer customerToUpdate = maybeCustomerToUpdate.get();
            customerToUpdate.setSurname(customerDto.getSurname());
            customerToUpdate.setName(customerDto.getName());
            customerToUpdate.setLastUpdatedBy(customerDto.getLastUpdatedBy());
            customerToUpdate.setPhotoUrl(customerDto.getPhotoUrl());
            customerRepository.saveAndFlush(customerToUpdate);
            return customerMapper.toDto(customerToUpdate);
        }

        throw new CustomerNotFoundException("Customer not fournd");
    }

    @Transactional
    @Override
    public void deleteCustomer(UUID id) {
        customerRepository.deleteCustomerById(id);
    }

    @Override
    public String uploadToS3(MultipartFile file) {
        try {
            S3Util.uploadFile(file);
            return Constants.S3_ROUTE + file.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void ValidateCustomer(CustomerDto customerDto) throws AttributeNotPresentException {
        Validations.when(customerDto == null ).throwIllegalArgument(Constants.CUSTOMER_REQUIRED);
        Validations.when(customerDto.getName() == null || customerDto.getName().isEmpty()).throwIllegalArgument(Constants.NAME_REQUIRED);
        Validations.when(customerDto.getSurname() == null || customerDto.getSurname().isEmpty()).throwIllegalArgument(Constants.SURNAME_REQUIRED);
    }
}
