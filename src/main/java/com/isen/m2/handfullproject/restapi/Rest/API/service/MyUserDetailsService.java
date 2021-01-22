package com.isen.m2.handfullproject.restapi.Rest.API.service;

import com.isen.m2.handfullproject.restapi.Rest.API.domain.User;
import com.isen.m2.handfullproject.restapi.Rest.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository users;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        final Optional<User> optionalUser = users.findByEmail(login);
        if(!optionalUser.isPresent()){
            throw new UsernameNotFoundException(MessageFormat.format("User with email {0} cannot be found",login));
        }

        return optionalUser.get();
    }
}
