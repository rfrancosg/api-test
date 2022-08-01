package com.monkeys.api_test.repositories;

import com.monkeys.api_test.data.models.entities.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends GenericRepository<User, Integer>
{
    Optional<User> findUserById(UUID uuid);
    Optional<User> findUserByEmail(String email);

    void deleteUserById(UUID uuid);
}