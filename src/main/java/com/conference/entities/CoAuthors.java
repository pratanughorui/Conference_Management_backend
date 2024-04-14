package com.conference.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "co-authors")
@Getter
@Setter
@NoArgsConstructor
public class CoAuthors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int coauthor_id;
    private String name;
    private String email;
    private String mobile;
    private String affiliation;
    private String country;
    private String googleScholarId;
    private String orchidId;
    @ManyToOne
    @JoinColumn(name = "authors_work_id")
    private Authors_work authors_work;
}
