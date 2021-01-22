package com.isen.m2.handfullproject.restapi.Rest.API.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority implements GrantedAuthority {
    @Id @Column
    @GeneratedValue
    private Long id;

    private String authority;

    public Authority(){
        super();
    }
    public Authority(String authority){
        super();
        this.authority = authority;
    }
}
