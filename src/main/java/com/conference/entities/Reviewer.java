package com.conference.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Reviewer")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reviewer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewer_id;
    private String name;
    private String address;
    private String place;
    private String state;
    private String affliation;
    private String country;
    private String password;
    private String mobile;
    @Column(unique = true)
    private String email;

    @ManyToMany
    @JoinTable(name = "track_reviewer", joinColumns = @JoinColumn(name = "reviewer_id"), inverseJoinColumns = @JoinColumn(name = "track_id"))
    private List<Track> tracks = new ArrayList<>();

    // @ManyToMany(mappedBy = "reviewers")
    // private List<Authors_work> authorWorks = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "allotments", joinColumns = @JoinColumn(name = "reviewer_id"), inverseJoinColumns = @JoinColumn(name = "author_work_id"))
    private List<Authors_work> authorWorks = new ArrayList<>();
}
