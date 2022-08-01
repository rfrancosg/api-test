package com.monkeys.api_test.services.implementations;

import com.monkeys.api_test.data.mappers.implementations.UserMapperImpl;
import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.data.models.entities.User;
import com.monkeys.api_test.exceptions.AttributeNotPresentException;
import com.monkeys.api_test.exceptions.UserNotFoundException;
import com.monkeys.api_test.repositories.UserRepository;
import com.monkeys.api_test.services.interfaces.UserService;
import com.monkeys.api_test.utils.Constants;
import com.monkeys.api_test.utils.Utils;
import com.monkeys.api_test.utils.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapperImpl userMapper;

    @Autowired
    Utils utils;

    @Override
    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(x -> userMapper.toDto(x)).collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(UserDto userDto) throws UserNotFoundException, AttributeNotPresentException {
        ValidateUser(userDto);
        Optional<User> maybeUserToUpdate = userRepository.findUserById(userDto.getId());
        if (maybeUserToUpdate.isPresent()){
            User userToUpdate = maybeUserToUpdate.get();
            userToUpdate.setEmail(userDto.getEmail());
            userToUpdate.setAdmin(userDto.isAdmin());
            userToUpdate.setSurname(userDto.getSurname());
            userToUpdate.setName(userDto.getName());
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty())
                userToUpdate.setPassword(utils.generateEncryptedPassword(userDto.getPassword()));
            userRepository.saveAndFlush(userToUpdate);
            return userMapper.toDto(userToUpdate);
        }

        throw new UserNotFoundException("User not fournd");
    }

    @Override
    public UserDto createUser(UserDto userDto) throws AttributeNotPresentException {
        ValidateUser(userDto);
        userDto.setId(utils.createUUID());
        User userToCreate = userMapper.toEntity(userDto);
        userToCreate.setPassword(utils.generateEncryptedPassword(userDto.getPassword()));
        User user = userRepository.saveAndFlush(userToCreate);
        return userMapper.toDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteUserById(id);
    }

    private void ValidateUser(UserDto userDto) throws AttributeNotPresentException {
        Validations.when(userDto == null ).throwIllegalArgument(Constants.CUSTOMER_REQUIRED);
        Validations.when(userDto.getName() == null || userDto.getName().isEmpty()).throwIllegalArgument(Constants.NAME_REQUIRED);
        Validations.when(userDto.getSurname() == null || userDto.getSurname().isEmpty()).throwIllegalArgument(Constants.SURNAME_REQUIRED);
        Validations.when(userDto.getEmail() == null || userDto.getEmail().isEmpty()).throwIllegalArgument(Constants.EMAIL_REQUIRED);
    }

}
