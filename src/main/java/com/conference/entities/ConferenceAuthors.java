// package com.conference.entities;

// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToOne;
// import jakarta.persistence.Table;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
// import lombok.Setter;

// @Entity
// @Table(name = "conference_authors")
// @Getter
// @Setter
// @NoArgsConstructor
// public class ConferenceAuthors {

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private int id;
// @ManyToOne
// @JoinColumn(name = "author_id")
// private Authors authors;

// @ManyToOne
// @JoinColumn(name = "conference_id")
// private Conference conference;

// }
