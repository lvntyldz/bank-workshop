package com.ba.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CampusDTO {

    private Long id;

    private String isim;

    private String webSiteUrl;

    private String telefon;

    private String eposta;

    private Set<ClassroomDTO> siniflar = new HashSet<>();

}
