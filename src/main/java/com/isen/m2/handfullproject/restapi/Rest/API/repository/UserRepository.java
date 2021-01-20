package com.isen.m2.handfullproject.restapi.Rest.API.repository;

import com.isen.m2.handfullproject.restapi.Rest.API.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
}