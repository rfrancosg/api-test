package com.monkeys.api_test.services.interfaces;

import com.monkeys.api_test.data.models.dto.CustomerDto;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.CustomerNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface CustomerService {

    /**
     * Returns all customers
     * @return
     */
    List<CustomerDto> getCustomers();

    /**
     * Returns a customer by id
     * @param id
     * @return
     */
    CustomerDto getCustomerById(UUID id) throws CustomerNotFoundException;

    /**
     * creates a customer
     * @param customerDto
     * @return
     * @throws AttributeNotPresentException
     */
    CustomerDto createCustomer(CustomerDto customerDto) throws AttributeNotPresentException;

    /**
     * updates a customer
     * @param customerDto
     * @return
     * @throws AttributeNotPresentException
     * @throws CustomerNotFoundException
     */
    CustomerDto updateCustomer(CustomerDto customerDto) throws AttributeNotPresentException, CustomerNotFoundException;

    /**
     * deletes a customer
     * @param id
     */
    void deleteCustomer(UUID id);

    String uploadToS3(MultipartFile file);
}
