package com.monkeys.api_test.services.implementations;

import com.monkeys.api_test.data.mappers.interfaces.UserMapper;
import com.monkeys.api_test.data.models.dto.LoginDto;
import com.monkeys.api_test.data.models.dto.UserDto;
import com.monkeys.api_test.data.models.entities.User;
import com.monkeys.api_test.repositories.UserRepository;
import com.monkeys.api_test.security.JwtTokenUtil;
import com.monkeys.api_test.services.interfaces.LoginService;
import com.monkeys.api_test.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Transactional
@EnableWebSecurity
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Autowired
    Utils utils;

    @Autowired
    JwtTokenUtil jwtTokenUtil;



    @Override
    public UserDto login(LoginDto loginDto) throws Exception {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        Optional<User> maybeUser = userRepository.findUserByEmail(loginDto.getEmail());
        if (maybeUser.isPresent()){
            if (utils.checkPassword(loginDto.getPassword() ,maybeUser.get().getPassword())){
                UserDto userDto = userMapper.toDto(maybeUser.get());
                userDto.setToken(jwtTokenUtil.doGenerateToken(authentication));
                return userDto;
            }
        }

        throw new Exception();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> maybeUser = userRepository.findUserByEmail(username);
        if (maybeUser.isPresent()){
            return build(maybeUser.get());
        }
        return null;
    }

    private static UserDetails build(User user) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthorities(user));
    }

    private static Set<? extends GrantedAuthority> getAuthorities(User retrievedUser) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        if (retrievedUser.isAdmin()){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return authorities;
    }


}
