package com.conference.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Role")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Role {
    @Id
    private int role_id;
    private String role_name;
    // @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY)
    // private List<Users> user = new ArrayList<>();
    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

}
