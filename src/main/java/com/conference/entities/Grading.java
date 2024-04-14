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
@Table(name = "grading")
@Getter
@Setter
@NoArgsConstructor
public class Grading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int gradeId;
    private String max_grade;
    private String min_grade;
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review reviews;
}
