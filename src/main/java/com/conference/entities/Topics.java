package com.conference.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Topics")
@Getter
@Setter
@NoArgsConstructor
public class Topics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topic_id;
    private String topic_name;
    @ManyToOne
    @JoinColumn(name = "track_id")
    private Track track;

    @OneToMany(mappedBy = "topics")
    private List<Authors_work> authors = new ArrayList<>();
}
