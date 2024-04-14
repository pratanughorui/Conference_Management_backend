package com.conference.services.imple;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.conference.config.AppConstants;
import com.conference.entities.Conference;
import com.conference.entities.Role;
import com.conference.entities.Users;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.ConferenceDto;
import com.conference.payloads.RoleDto;
import com.conference.payloads.UserDto;
import com.conference.repositories.ConferenceRepo;
import com.conference.repositories.RoleRepo;
import com.conference.repositories.UserRepo;
import com.conference.services.CommitteemamberService;
import com.conference.config.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Service
public class CommitteemamberServiceImple implements CommitteemamberService {
    @Autowired
    private ConferenceRepo conferenceRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    /*
     * @Override
     * public void CreateCommitteeMember(UserDto userDto) {
     * String conference_name = userDto.getConference_name();
     * // System.out.println("hisisisisiissi");
     * Users existingUser = this.userRepo.findByEmail(userDto.getEmail());
     * if (existingUser != null) {
     * Set<Role> existingRoles = existingUser.getRoles();
     * Role newRole = this.roleRepo.findByRole_name("Programme Committee");
     * Set<Conference> existingConferences = existingUser.getConferences();
     * Conference conference =
     * this.conferenceRepo.findByConference_name(conference_name);
     * if (conference == null) {
     * throw new ResourceNotFoundException("Conference", "conference_name: " +
     * conference_name, 1);
     * }
     * if (existingRoles.contains(newRole) &&
     * existingConferences.contains(conference)) {
     * // handle this error
     * throw new
     * DataIntegrityViolationException("User already has the specified role in the same conference"
     * );
     * } else {
     * existingConferences.add(conference);
     * existingUser.setConferences(existingConferences);
     * existingRoles.add(newRole);
     * existingUser.setRoles(existingRoles);
     * Users x = this.userRepo.save(existingUser);
     * Set<Users> u = conference.getUser();
     * u.add(x);
     * conference.setUser(u);
     * this.conferenceRepo.save(conference);
     * 
     * }
     * 
     * } else {
     * Users newuser = this.dtoTouser(userDto);
     * Set<Role> role = this.roleRepo.findByAllRole_name("Programme Committee");
     * Set<Conference> conference =
     * this.conferenceRepo.findByAllConference_name(conference_name);
     * if (conference.isEmpty()) {
     * throw new ResourceNotFoundException("Conference", "conference_name: " +
     * conference_name, 1);
     * }
     * newuser.setConferences(conference);
     * newuser.setRoles(role);
     * this.userRepo.save(newuser);
     * }
     * 
     * // }
     * // Users existingUser = this.userRepo.findByEmail(userDto.getEmail());
     * // Conference conference =
     * // this.conferenceRepo.findByConference_name(userDto.getConference_name());
     * // if (existingUser != null) {
     * // // User already exists, handle accordingly
     * // existingUser.getConferences().add(conference);
     * // this.userRepo.save(existingUser);
     * // } else {
     * // Users newUser = this.dtoTouser(userDto);
     * // // Set other user details from userDto
     * 
     * // newUser.getConferences().add(conference);
     * 
     * // this.userRepo.save(newUser);
     * // }
     * 
     * }
     */

    public Users dtoTouser(UserDto userDto) {
        Users user = new Users();
        user.setAddress(userDto.getAddress());
        user.setUser_id(userDto.getUser_id());
        // user.setConference_name(userDto.getConference_name());
        // user.setUser_type(userDto.getUser_type());
        user.setEmail(userDto.getEmail());
        user.setMobile(userDto.getMobile());
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        return user;

    }

    // public UserDto userTodto(Users user) {
    // // ConferenceServiceImple cs = new ConferenceServiceImple();
    // // RoleServiceImple rs = new RoleServiceImple();
    // // ConferenceDto conferenceDto = this.modelMapper.map(conference,
    // // ConferenceDto.class);
    // // Set<RoleDto> roleDto = user.getRoles().stream().map(con ->
    // // this.modelMapper.map(con, RoleDto.class))
    // // .collect(Collectors.toSet());
    // UserDto userDto = new UserDto();
    // userDto.setUser_id(user.getUser_id());
    // userDto.setAddress(user.getAddress());
    // userDto.setEmail(user.getEmail());
    // userDto.setMobile(user.getMobile());
    // userDto.setName(user.getName());
    // // userDto.setConference_name(user.getConference_name());
    // // userDto.setUser_type(user.getUser_type());
    // userDto.setPassword(user.getPassword());
    // userDto.setConference(new
    // ConferenceServiceImple().entityTodto(user.getConference()));
    // Set<RoleDto> ans = new HashSet<>();
    // for (Role r : user.getRoles()) {
    // RoleDto rt = this.roleTodto(r);
    // ans.add(rt);
    // }
    // userDto.setRoles(ans);
    // // userDto.setRoles(roleDto);

    // return userDto;

    // }

    public RoleDto roleTodto(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setRole_id(role.getRole_id());
        roleDto.setRole_name(role.getRole_name());
        return roleDto;
    }
}
