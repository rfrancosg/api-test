package com.monkeys.api_test.controllers;

import com.monkeys.api_test.data.models.dto.CustomerDto;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.CustomerNotFoundException;
import com.monkeys.api_test.services.interfaces.CustomerService;
import com.monkeys.api_test.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(Constants.CUSTOMER_PATH)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping(value = Constants.ALL_PATH, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<CustomerDto> getAllCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping(value = Constants.ID_PARAM, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CustomerDto getCustomerById(@PathVariable(Constants.PATH_PARAM) UUID id) throws CustomerNotFoundException {
        return customerService.getCustomerById(id);
    }

    @PostMapping(value = Constants.CREATE_PATH, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) throws AttributeNotPresentException {
        return customerService.createCustomer(customerDto);
    }

    @PostMapping(value = Constants.UPLOAD_IMAGE_PATH, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    @Consumes({MediaType.MULTIPART_FORM_DATA_VALUE})
    public String uploadToS3(@RequestParam(Constants.FILE_PARAM) MultipartFile file) {
        return customerService.uploadToS3(file);
    }

    @PutMapping(value = Constants.UPDATE_PATH, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) throws AttributeNotPresentException, CustomerNotFoundException {
        return customerService.updateCustomer(customerDto);
    }

    @DeleteMapping(value = Constants.CUSTOMER_PATH_PARAM, produces = {Constants.APPLICATION_JSON})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void deleteCustomer(@PathVariable(Constants.CUSTOMER_ID_PATH_PARAM) UUID customerId) {
        customerService.deleteCustomer(customerId);
    }

}
