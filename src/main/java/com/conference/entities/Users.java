package com.conference.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int user_id;
  private String name;
  private String address;
  // private String conference_name;
  // private String user_type;
  private String place;
  private String state;
  private String country;
  private String password;
  private String mobile;
  @Column(unique = true)
  private String email;
  // @ManyToOne
  // @JoinColumn(name = "conference_id")
  // private Conference conference;

  @ManyToMany
  @JoinTable(name = "committee_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "committee_id"))
  private List<Committee> committee = new ArrayList<>();

  // @ManyToMany(mappedBy = "user")
  // private Set<Conference> conferences = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "conference_id")
  private Conference conference;

  @ManyToMany
  @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  private Set<Role> roles = new HashSet<>();

  // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
  // private Author_Work authorWork;

}
