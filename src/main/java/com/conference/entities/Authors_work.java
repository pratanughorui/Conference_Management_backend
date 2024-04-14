package com.conference.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.Generated;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authors_work")
@Getter
@Setter
@NoArgsConstructor
public class Authors_work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int author_id;
    private String name;
    // private String address;
    private String affiliation;
    // private String city;
    // private String state;
    private String country;
    private String cont_no;
    private String email;
    private String google_Scholar_id;
    private String orchid_id;
    private String title;
    private String track;
    private String key_words;
    @Column(columnDefinition = "TEXT")
    private String abstractText;
    private String pdf_name;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topics topics;

    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conferences;

    @ManyToMany(mappedBy = "authorWorks")
    private List<Reviewer> reviewers = new ArrayList<>();

    @OneToMany(mappedBy = "authors_work", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoAuthors> CoAuthors = new ArrayList<>();
}