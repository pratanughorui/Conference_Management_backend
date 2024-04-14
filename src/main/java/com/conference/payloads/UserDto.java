package com.conference.payloads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.conference.entities.Conference;
import com.conference.entities.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {
    private int user_id;
    @NotEmpty
    private String name;

    private String address;
    private String place;
    private String state;
    private String country;
    private String roleName;
    @NotEmpty
    private String password;
    @NotEmpty
    private String mobile;
    @NotEmpty
    @Email(message = "email address is not valid")
    private String email;
    private List<ConferenceDto> conferences = new ArrayList<>();
    @JsonIgnore
    private Set<RoleDto> roles = new HashSet<>();
    // private RoleDto role;
    // private ConferenceDto conference;
}
