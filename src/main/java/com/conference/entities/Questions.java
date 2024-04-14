package com.conference.entities;

import jakarta.persistence.Column;
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
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionsId;
    @Column(columnDefinition = "TEXT")
    private String qus;
    @Column(columnDefinition = "TEXT")
    private String ans;
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review reviews;
}
