package com.isen.m2.handfullproject.restapi.Rest.API.controller;

import com.isen.m2.handfullproject.restapi.Rest.API.Exception.UserAlreadyExistException;
import com.isen.m2.handfullproject.restapi.Rest.API.domain.User;
import com.isen.m2.handfullproject.restapi.Rest.API.form.UserForm;
import com.isen.m2.handfullproject.restapi.Rest.API.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.text.MessageFormat;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userDetailsService;

    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/register")
    public String register(WebRequest request, Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("user",userForm);
        return "register";
    }

    @PostMapping("/user/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserForm userForm, BindingResult result, Model model) {
        try {
            User registered = userDetailsService.registerNewUserAccount(userForm);
        } catch (UserAlreadyExistException uaeEx) {
            return "register";
        }
        logger.info(MessageFormat.format("User with email {1} has been created",userForm.getEmail()));

        return "welcome";
    }
}
