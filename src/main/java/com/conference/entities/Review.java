package com.conference.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Review")
@Getter
@Setter
@NoArgsConstructor
public class Review<CoAuthors> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private int reviewerId;
    private int paperId;
    private String reviewDate;
    private String acceptance;
    private int totalScore;
    @OneToMany(mappedBy = "reviews", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Questions> questionss = new ArrayList<>();
    @OneToMany(mappedBy = "reviews", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grading> grading = new ArrayList<>();
}
