package com.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.conference.entities.Role;
import java.util.List;
import java.util.Set;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.role_name = :role_name")
    Set<Role> findByAllRole_name(@Param("role_name") String role_name);

    @Query("SELECT c FROM Role c WHERE c.role_name = :role_name")
    Role findByRole_name(@Param("role_name") String role_name);
}
