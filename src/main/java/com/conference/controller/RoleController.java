package com.conference.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.conference.entities.Role;
import com.conference.payloads.ConferenceDto;
import com.conference.payloads.RoleDto;
import com.conference.repositories.RoleRepo;

@CrossOrigin("*")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/getallrole")
    public List<RoleDto> getAllRoles() {
        List<Role> roles = this.roleRepo.findAll();

        List<RoleDto> allroles = roles.stream()
                .filter(role -> role.getRole_id() != 505) // Filter out the role with ID 505
                .map(role -> this.modelMapper.map(role, RoleDto.class))
                .collect(Collectors.toList());
        return allroles;

    }
}
