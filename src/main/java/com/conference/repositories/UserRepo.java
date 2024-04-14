package com.conference.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.conference.entities.Conference;
import com.conference.entities.Role;
import com.conference.entities.Users;

public interface UserRepo extends JpaRepository<Users, Integer> {
    // List<Users> findByRole(Role role);

    // List<Users> findByConference(Conference conference);
    @Query("SELECT c FROM Users c WHERE c.email = :email")
    Users findByEmail(@Param("email") String email);
}
