package com.isen.m2.handfullproject.restapi.Rest.API.form;

import com.isen.m2.handfullproject.restapi.Rest.API.validator.PasswordValidator;
import com.isen.m2.handfullproject.restapi.Rest.API.validator.ValidEmail;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserForm {
    private Long id;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @PasswordValidator
    @NotNull
    @NotEmpty
    private String matchingPassword;
}
