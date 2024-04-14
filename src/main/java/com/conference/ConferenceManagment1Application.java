package com.conference;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.conference.config.AppConstants;
import com.conference.entities.Role;
import com.conference.repositories.RoleRepo;

@SpringBootApplication
public class ConferenceManagment1Application implements CommandLineRunner {
	@Autowired
	private RoleRepo rolerepo;

	public static void main(String[] args) {
		SpringApplication.run(ConferenceManagment1Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role1 = new Role();
			role1.setRole_id(AppConstants.committee_member1);
			role1.setRole_name("committee_member1");
			Role role2 = new Role();
			role2.setRole_id(AppConstants.committee_member2);
			role2.setRole_name("committee_member2");
			Role role3 = new Role();
			role3.setRole_id(AppConstants.committee_member3);
			role3.setRole_name("committee_member3");
			Role role4 = new Role();
			role4.setRole_id(AppConstants.committee_member4);
			role4.setRole_name("committee_member4");
			List<Role> result = List.of(role1, role2, role3, role4);
			this.rolerepo.saveAll(result);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
