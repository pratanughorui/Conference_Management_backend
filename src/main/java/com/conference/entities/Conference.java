package com.conference.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Conference")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Conference {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int conference_id;
  @Column(unique = true)
  private String conferences_title;
  private String website;
  private String venue;
  private String address;
  private String place;
  private String state;
  private String country;
  private String fromDate;
  private String toDate;
  private String datecallpaper;
  private String lastdatesubpaper;
  private String dateofallotpaper;
  private String lastdaterevsub;

  @CreationTimestamp // This annotation automatically populates the field with the current timestamp
                     // on entity creation

  @Column(name = "creation_date_time", updatable = false)
  private String creationDateTimeAsString;

  @CreationTimestamp
  @Transient // This annotation prevents the field from being persisted to the database
  private LocalDateTime creationDateTime;

  public String getCreationDateTimeAsString() {
    if (creationDateTime != null) {
      // Convert LocalDateTime to a string in the desired format
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      return creationDateTime.format(formatter);
    } else {
      return null;
    }
  }

  public void setCreationDateTimeAsString(String creationDateTimeAsString) {
    this.creationDateTimeAsString = creationDateTimeAsString;
    if (creationDateTimeAsString != null) {
      // Parse the string to LocalDateTime
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      this.creationDateTime = LocalDateTime.parse(creationDateTimeAsString, formatter);
    } else {
      this.creationDateTime = null;
    }
  }
  // @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
  // private Set<Author_Work> author_Works = new HashSet<>();

  // @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval
  // = true)
  // private Set<Author_Work> author_Works;

  // @ManyToMany
  // @JoinTable(name = "conference_user", joinColumns = @JoinColumn(name =
  // "conference_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  // private Set<Users> attendees;

  // @ManyToMany(mappedBy = "conferences")
  // private List<Reviewer> reviewers = new ArrayList<>();

  // @ManyToMany
  // @JoinTable(name = "conference_user", joinColumns = @JoinColumn(name =
  // "conference_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  // private Set<Users> user = new HashSet<>();

  // @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval
  // = true)
  // private Set<Authors> author;
  @OneToMany(mappedBy = "conference", cascade = CascadeType.REMOVE)
  private List<Users> user;

  @OneToMany(mappedBy = "conference", cascade = CascadeType.REMOVE)
  private List<Track> tracks;

  @OneToMany(mappedBy = "conference", cascade = CascadeType.REMOVE)
  private List<Committee> committees;

  @OneToMany(mappedBy = "conferences", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Authors_work> authors = new ArrayList<>();

  // public void setauthor_Works(Author_Work author_Work) {
  // this.author_Works.add(author_Work);
  // }

  // public void setuser(Users user) {
  // this.user.add(user);
  // }

  // public Set<Author_Work> getauthor_Works() {
  // return author_Works;
  // }

  // public Set<Users> getuser() {
  // return user;
  // }
}
