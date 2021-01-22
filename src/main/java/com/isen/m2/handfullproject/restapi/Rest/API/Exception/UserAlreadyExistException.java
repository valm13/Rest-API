package com.isen.m2.handfullproject.restapi.Rest.API.Exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String errorMessage) {
        super(errorMessage);
    }
}