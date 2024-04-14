package com.conference.services.imple;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.conference.config.AppConstants;
import com.conference.entities.Committee;
import com.conference.entities.Conference;
import com.conference.entities.Role;
import com.conference.entities.Users;
import com.conference.exceptions.ResourceNotFoundException;
import com.conference.payloads.ConferenceDto;
import com.conference.payloads.RoleDto;
import com.conference.payloads.UserDto;
import com.conference.repositories.CommitteeRepo;
import com.conference.repositories.ConferenceRepo;
import com.conference.repositories.RoleRepo;
import com.conference.repositories.UserRepo;
import com.conference.services.UserService;
import com.conference.services.ConferenceService;

@Service
public class UserServiceImple implements UserService {
        @Autowired
        private UserRepo userRepo;
        @Autowired
        private ConferenceRepo conferenceRepo;
        @Autowired
        private RoleRepo roleRepo;
        @Autowired
        private ModelMapper modelMapper;

        @Autowired
        private CommitteeRepo committeeRepo;
        // @Autowired
        // private PasswordEncoder passwordEncoder;

        @Override
        public boolean createUser(List<UserDto> userDto, Integer committee_id, Integer conference_id) {
                Committee committee = this.committeeRepo.findById(committee_id)
                                .orElseThrow(() -> new ResourceNotFoundException("Committee", "id",
                                                committee_id));
                Conference conference = this.conferenceRepo.findById(conference_id)
                                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id",
                                                conference_id));
                for (UserDto user : userDto) {
                        String email = user.getEmail();
                        Users existuser = this.userRepo.findByEmail(email);
                        Role role = this.roleRepo.findByRole_name(user.getRoleName());
                        if (existuser == null) {
                                Users newuser = this.modelMapper.map(user, Users.class);
                                System.out.println(newuser.toString());
                                newuser.getRoles().add(role);
                                newuser.getCommittee().add(committee);
                                newuser.setConference(conference);
                                this.userRepo.save(newuser);
                        } else {
                                if (committee.getUser().contains(existuser) &&
                                                existuser.getRoles().contains(role)) {
                                        throw new DataIntegrityViolationException("this user is already exist");
                                } else if (existuser.getRoles().contains(role)) {
                                        existuser.getCommittee().add(committee);
                                } else if (committee.getUser().contains(existuser)) {
                                        existuser.getRoles().add(role);
                                } else {
                                        existuser.getCommittee().add(committee);
                                        existuser.getRoles().add(role);

                                }
                                existuser.setConference(conference);
                                this.userRepo.save(existuser);

                        }

                }
                return true;

                // Conference conference = this.conferenceRepo.findById(conference_id)
                // .orElseThrow(() -> new ResourceNotFoundException("Conference", "id",
                // conference_id));
                // Role role = this.roleRepo.findById(role_id)
                // .orElseThrow(() -> new ResourceNotFoundException("role", "id", role_id));
                // String email = userDto.getEmail();
                // // i have to create a condition later when i will merge between conference
                // and
                // // user
                // Users existuser = this.userRepo.findByEmail(email);
                // if (existuser == null) {
                // Users user = this.modelMapper.map(userDto, Users.class);
                // user.getRoles().add(role);
                // user.getConferences().add(conference);
                // Users savedsuser = this.userRepo.save(user);
                // return this.modelMapper.map(savedsuser, UserDto.class);
                // } else {
                // if (conference.getUser().contains(existuser) &&
                // existuser.getRoles().contains(role)) {
                // throw new DataIntegrityViolationException("this user is already exist");
                // } else if (existuser.getRoles().contains(role)) {
                // existuser.getConferences().add(conference);
                // } else if (conference.getUser().contains(existuser)) {
                // existuser.getRoles().add(role);
                // } else {
                // existuser.getConferences().add(conference);
                // existuser.getRoles().add(role);

                // }
                // Users savedsuser = this.userRepo.save(existuser);
                // return this.modelMapper.map(savedsuser, UserDto.class);

                // }

        }

        // @Override
        // public boolean createReviewer(List<UserDto> userDto, Integer conference_id) {
        // Conference conference = this.conferenceRepo.findById(conference_id)
        // .orElseThrow(() -> new ResourceNotFoundException("Conference", "id",
        // conference_id));
        // Role role = this.roleRepo.findById(505)
        // .orElseThrow(() -> new ResourceNotFoundException("role", "id",
        // 505));
        // for (UserDto user : userDto) {
        // String email = user.getEmail();
        // Users existuser = this.userRepo.findByEmail(email);
        // if (existuser == null) {
        // Users newuser = this.modelMapper.map(user, Users.class);
        // newuser.getRoles().add(role);
        // newuser.getConferences().add(conference);
        // this.userRepo.save(newuser);
        // } else {
        // if (conference.getUser().contains(existuser) &&
        // existuser.getRoles().contains(role)) {
        // throw new DataIntegrityViolationException("this user is already exist");
        // } else if (existuser.getRoles().contains(role)) {
        // existuser.getConferences().add(conference);
        // } else if (conference.getUser().contains(existuser)) {
        // existuser.getRoles().add(role);
        // } else {
        // existuser.getConferences().add(conference);
        // existuser.getRoles().add(role);

        // }
        // this.userRepo.save(existuser);

        // }

        // }

        // return true;
        // }

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
        // ConferenceServiceImple().entityTodto(user.getConferences()));
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

        @Override
        public UserDto updateUser(UserDto userDto, Integer user_id) {
                // TODO Auto-generated method stub
                Users user = this.userRepo.findById(user_id)
                                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", user_id));
                user.setAddress(userDto.getAddress());
                user.setEmail(userDto.getEmail());
                user.setMobile(userDto.getMobile());
                user.setName(userDto.getName());
                user.setPassword(userDto.getPassword());
                Users updatUsers = this.userRepo.save(user);
                return this.modelMapper.map(updatUsers, UserDto.class);

                // throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
        }

        @Override
        public void deleteUserById(Integer user_id) {
                // TODO Auto-generated method stub
                Users user = this.userRepo.findById(user_id)
                                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", user_id));
                this.userRepo.deleteById(user_id);

                // throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
        }

        @Override
        public List<UserDto> getAllUser() {
                // TODO Auto-generated method stub
                // List<Users> user = this.userRepo.findAll();
                // List<UserDto> userDto = user.stream().map(con ->
                // this.userTodto(con)).collect(Collectors.toList());
                return null;
                // throw new UnsupportedOperationException("Unimplemented method 'getAllUser'");
        }

        @Override
        public List<UserDto> getAllUserBeforeRecentDate() {
                LocalDateTime currentDate = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String now = currentDate.format(formatter);
                List<Conference> conferences = this.conferenceRepo.findAllConferencesBeforeDate(now);
                List<Users> users = new ArrayList<>();
                for (Conference c : conferences) {
                        users.addAll(c.getUser());
                }
                List<UserDto> users2 = users.stream().map(con -> this.modelMapper.map(con, UserDto.class))
                                .collect(Collectors.toList());
                return users2;

        }

        @Override
        public UserDto getUserById(Integer user_id) {
                // TODO Auto-generated method stub
                Users user = this.userRepo.findById(user_id)
                                .orElseThrow(() -> new ResourceNotFoundException("Users", "id", user_id));

                return this.modelMapper.map(user, UserDto.class);
                // throw new UnsupportedOperationException("Unimplemented method
                // 'getUserById'");
        }

        @Override
        public List<UserDto> getallreviewers(Integer conference_id) {
                Conference conference = this.conferenceRepo.findById(conference_id)
                                .orElseThrow(() -> new ResourceNotFoundException("Conference", "id",
                                                conference_id));
                List<Users> user = conference.getUser();
                List<UserDto> reviewers = user.stream()
                                .filter(users -> users.getRoles().stream().anyMatch(role -> role.getRole_id() == 505))
                                .map(con -> this.modelMapper.map(con, UserDto.class))
                                .collect(Collectors.toList());
                return reviewers;

        }

        // @Override
        // public List<UserDto> getAllUserByRole(Integer role_id) {
        // // TODO Auto-generated method stub
        // Role role = this.roleRepo.findById(role_id)
        // .orElseThrow(() -> new ResourceNotFoundException("Role", "id", role_id));

        // List<Users> user = this.userRepo.findByRole(role);
        // List<UserDto> userDto = user.stream()
        // .map(con -> this.modelMapper.map(con, UserDto.class))
        // .collect(Collectors.toList());
        // return userDto;
        // // throw new UnsupportedOperationException("Unimplemented method
        // // 'getAllUserByRole'");
        // }

        // @Override
        // public List<UserDto> getAllUserByConference(Integer conference_id) {
        // Conference conference = this.conferenceRepo.findById(conference_id)
        // .orElseThrow(() -> new ResourceNotFoundException("Conference", "id",
        // conference_id));
        // List<Users> user = this.userRepo.findByConference(conference);
        // List<UserDto> userDto = user.stream()
        // .map(con -> this.modelMapper.map(con, UserDto.class))
        // .collect(Collectors.toList());
        // return userDto;
        // }

}