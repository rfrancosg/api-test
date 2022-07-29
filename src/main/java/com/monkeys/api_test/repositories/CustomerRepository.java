package com.monkeys.api_test.repositories;

import com.monkeys.api_test.models.entities.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer, Integer>
{

}