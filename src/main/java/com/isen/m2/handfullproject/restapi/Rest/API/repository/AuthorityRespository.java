package com.isen.m2.handfullproject.restapi.Rest.API.repository;

import com.isen.m2.handfullproject.restapi.Rest.API.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRespository extends JpaRepository<Authority,Long> {
    Optional<Authority> findByAuthority(String authority);
}
