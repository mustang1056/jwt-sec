package com.sec.demo.jwtsec.repository;

import com.sec.demo.jwtsec.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
