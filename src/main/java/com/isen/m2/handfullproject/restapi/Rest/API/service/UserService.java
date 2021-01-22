package com.isen.m2.handfullproject.restapi.Rest.API.service;

import com.isen.m2.handfullproject.restapi.Rest.API.Exception.UserAlreadyExistException;
import com.isen.m2.handfullproject.restapi.Rest.API.domain.Authority;
import com.isen.m2.handfullproject.restapi.Rest.API.domain.User;
import com.isen.m2.handfullproject.restapi.Rest.API.form.UserForm;
import com.isen.m2.handfullproject.restapi.Rest.API.repository.AuthorityRespository;
import com.isen.m2.handfullproject.restapi.Rest.API.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthorityRespository authorities;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public User registerNewUserAccount(UserForm userForm)
            throws UserAlreadyExistException {

        if (emailExists(userForm.getEmail())) {
            throw new UserAlreadyExistException(MessageFormat.format("There is an account with that email address : {0}",userForm.getEmail()));
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        user.setEmail(userForm.getEmail());
        user.setLocked(false);
        user.setEnabled(true);
        Authority auth = createAuthority("ROLE_USER");
        List<Authority> authList = new ArrayList<>();
        authList.add(auth);
        user.setAuthorities(authList);

        repository.save(user);
        return user;
    }
    private boolean authorityExists(String authority){return authorities.findByAuthority(authority).isPresent();}

    private Authority createAuthority(String authority){
        if(authorityExists(authority)) {
            return authorities.findByAuthority(authority).get();
        }
        Authority auth = new Authority(authority);
        authorities.save(auth);
        return auth;
    }

    private boolean emailExists(String email) {
        return repository.findByEmail(email).isPresent();
    }
}
