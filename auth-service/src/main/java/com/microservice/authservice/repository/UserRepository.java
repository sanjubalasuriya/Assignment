package com.microservice.authservice.repository;

import com.microservice.authservice.model.Role;
import com.microservice.authservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);


    User findByRole(Role role);
}
