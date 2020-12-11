package com.ba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBL_CAMPUS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Campus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String webSiteUrl;

    private String phone;

    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "campus_id")
    private Set<Classroom> classrooms = new HashSet<>();

}
