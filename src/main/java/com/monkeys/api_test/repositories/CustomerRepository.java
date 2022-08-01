package com.monkeys.api_test.repositories;

import com.monkeys.api_test.data.models.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends GenericRepository<Customer, Integer>
{
    Optional<Customer> findCustomerById(UUID uuid);

    void deleteCustomerById(UUID uuid);
}