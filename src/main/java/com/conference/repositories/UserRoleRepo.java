package com.conference.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.conference.entities.Role;
import java.util.List;

public interface UserRoleRepo extends JpaRepository<Role, Integer> {

}
