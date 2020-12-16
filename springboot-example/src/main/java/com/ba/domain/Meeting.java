package com.ba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBL_MEETING")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date startDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "TBL_MEETING_MEMBER", joinColumns = @JoinColumn(name = "MEETING_ID"), inverseJoinColumns = @JoinColumn(name = "MEMBER_ID"))
    private Set<Member> meetings = new HashSet<>();
}
