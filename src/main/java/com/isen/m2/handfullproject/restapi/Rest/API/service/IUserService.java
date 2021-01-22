package com.isen.m2.handfullproject.restapi.Rest.API.service;

import com.isen.m2.handfullproject.restapi.Rest.API.Exception.UserAlreadyExistException;
import com.isen.m2.handfullproject.restapi.Rest.API.domain.User;
import com.isen.m2.handfullproject.restapi.Rest.API.form.UserForm;

public interface IUserService {
    User registerNewUserAccount(UserForm userForm)
            throws UserAlreadyExistException;
}
