package com.ba.controller;

import com.ba.converter.CampusMapper;
import com.ba.domain.Campus;
import com.ba.domain.Classroom;
import com.ba.dto.CampusDTO;
import com.ba.dto.ClassroomDTO;
import com.ba.repository.CampusRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/mapper")
public class MapperController {

    @Autowired
    private CampusRepository campusRepository;

    @GetMapping("/one-to-many-dto")
    public String dtoOneToManyExample() {

        ClassroomDTO classroomDTO1 = new ClassroomDTO(null, "kütüphane", "D1001");
        ClassroomDTO classroomDTO2 = new ClassroomDTO(null, "derslik 12", "D1012");

        CampusDTO campusDTO = new CampusDTO(null, "üsküdar kampüsü", "http://uskudarkampus.com", "02160000012", "uskudar@kampus.com", Stream.of(classroomDTO1, classroomDTO2).collect(Collectors.toSet()));

        Campus campus = Mappers.getMapper(CampusMapper.class).toEntity(campusDTO);

        campusRepository.save(campus);

        return campus.toString();
    }

    @GetMapping("/one-to-many")
    public String oneToManyExample() {

        Classroom classroom1 = new Classroom(null, "derslik 13", "D10013");
        Classroom classroom2 = new Classroom(null, "derslik 14", "D10014");

        Campus campus1 = new Campus(null, "ataşehir kampus", "htto://atasehir.campus.com", "021600000013", "atasaherir@kampus.com", Stream.of(classroom1, classroom2).collect(Collectors.toSet()));

        campusRepository.save(campus1);

        CampusDTO campusDTO1 = Mappers.getMapper(CampusMapper.class).toDto(campus1);

        return campusDTO1.toString();
    }

    //TODO:Development[Bruadan devam]
    //readme file eklenecek
    //mapstruct anlatılacak
    //mapStruct ile oneToOne, oneToMany ve ManyToMany örneği yapılacak
    //Dependency ve implementasyon anlatılacak

}
