package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MeetingDTO {

    private Long id;

    private String name;

    private Date startDate;

    private Set<MemberDTO> toplantilar = new HashSet<>();

}
