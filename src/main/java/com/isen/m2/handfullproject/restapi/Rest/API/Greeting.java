package com.isen.m2.handfullproject.restapi.Rest.API;

import lombok.Getter;

@Getter
public class Greeting {
    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
