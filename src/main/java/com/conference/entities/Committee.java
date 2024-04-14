package com.conference.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "committee")
@Getter
@Setter
@NoArgsConstructor
public class Committee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int committee_id;
    private String committee_name;
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;

    @ManyToMany(mappedBy = "committee")
    private List<Users> user = new ArrayList<>();
}
