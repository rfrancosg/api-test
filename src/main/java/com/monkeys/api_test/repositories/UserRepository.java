package com.monkeys.api_test.repositories;

import com.monkeys.api_test.models.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends GenericRepository<User, Integer>
{

}