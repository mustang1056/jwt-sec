package com.sec.demo.jwtsec.repository;

import com.sec.demo.jwtsec.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
        User findByUsername(String name);
}
