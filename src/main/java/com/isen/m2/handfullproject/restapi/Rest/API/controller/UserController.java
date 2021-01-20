package com.isen.m2.handfullproject.restapi.Rest.API.controller;

import com.googlecode.javaewah.IteratorUtil;
import com.isen.m2.handfullproject.restapi.Rest.API.domain.User;
import com.isen.m2.handfullproject.restapi.Rest.API.form.UserForm;

import com.isen.m2.handfullproject.restapi.Rest.API.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.naming.Binding;
import java.util.Iterator;
import java.util.List;

@Controller
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository users;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public String registerUserAccount(UserForm form, BindingResult result, Model model) {
        if(result.hasErrors())
        {
            logger.error("Registration : has Error");
            model.addAttribute("user",form);
            return "register";
        }
        User u = new User();
        u.setEmail(form.getEmail());
        u.setPassword(passwordEncoder.encode(form.getPassword()));

        logger.info("Registration : OK");
        logger.info("Email : "+u.getEmail());
        logger.info("Password : "+u.getPassword());
        users.save(u);

        return "redirect:/user/login";
    }
}
