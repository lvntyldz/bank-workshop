package com.ba.controller;

import com.ba.converter.CampusMapper;
import com.ba.converter.DeliveryReceiptMapper;
import com.ba.converter.MeetingMapper;
import com.ba.converter.SystemUserMapper;
import com.ba.domain.*;
import com.ba.dto.*;
import com.ba.repository.CampusRepository;
import com.ba.repository.DeliveryReceiptRepository;
import com.ba.repository.MeetingRepository;
import com.ba.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/mapper")
public class MapperController {

    @Autowired
    private CampusRepository campusRepository;

    @Autowired
    private DeliveryReceiptRepository deliveryReceiptRepository;

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @GetMapping("/simple-example")
    public SystemUserDTO simpleExample() {

        SystemUser systemUser = new SystemUser(null, "admin", "a12345", "Ahmet YILMAZ", "ahmet@yilmaz.com", "05320000000", true, new Date());

        systemUserRepository.save(systemUser);

        SystemUserDTO dto = SystemUserMapper.INSTANCE.toDTO(systemUser);

        return dto;
    }

    @GetMapping("/simple-example-mismatched")
    public SystemUser differentTypeExample() {

        //SystemUserDTOTr da Id yok. Ayrıca password ve phone alanları da türkçe
        SystemUserDTOTr dtoTr = new SystemUserDTOTr("admin2", "a123", "veli velioğlu", "veli@aa.bb", "05550000000", false, new Date());
        SystemUser systemUser = SystemUserMapper.INSTANCE.toEntity(dtoTr);

        systemUserRepository.save(systemUser);

        return systemUser;
    }

    @GetMapping("/one-to-one")
    public DeliveryReceipt oneToOneExample() {

        ProductShipmentDTO productShipmentDTO = new ProductShipmentDTO(null, 123L, "http://yurtici.com/1234567", true);

        DeliveryReceiptDTO deliveryReceiptDTO = new DeliveryReceiptDTO(1L, "A12345", "Can YILMAZ", new Date(), productShipmentDTO);

        DeliveryReceipt deliveryReceipt = DeliveryReceiptMapper.INSTANCE.toEntity(deliveryReceiptDTO);

        deliveryReceiptRepository.save(deliveryReceipt);

        return deliveryReceipt;
    }

    @GetMapping("/one-to-one-dto")
    public DeliveryReceiptDTO oneToOneDtoExample() {

        ProductShipment productShipment = new ProductShipment(null, 456L, "http://aras.com/1234567", false);

        DeliveryReceipt deliveryReceipt = new DeliveryReceipt(2L, "B12345", "Ahmet YILMAZ", new Date(), productShipment);

        deliveryReceiptRepository.save(deliveryReceipt);

        DeliveryReceiptDTO deliveryReceiptDTO = DeliveryReceiptMapper.INSTANCE.toDTO(deliveryReceipt);

        return deliveryReceiptDTO;
    }

    @GetMapping("/one-to-many-dto")
    public CampusDTO oneToManyDtoExample() {

        Classroom classroom1 = new Classroom(null, "derslik 13", "D10013");
        Classroom classroom2 = new Classroom(null, "derslik 14", "D10014");

        Campus campus = new Campus(null, "ataşehir kampus", "htto://atasehir.campus.com", "021600000013", "atasaherir@kampus.com", Stream.of(classroom1, classroom2).collect(Collectors.toSet()));

        campusRepository.save(campus);

        CampusDTO campusDTO = CampusMapper.INSTANCE.toDto(campus);

        return campusDTO;
    }

    @GetMapping("/one-to-many")
    public Campus oneToManyExample() {

        ClassroomDTO classroomDTO1 = new ClassroomDTO(null, "kütüphane", "D1001");
        ClassroomDTO classroomDTO2 = new ClassroomDTO(null, "derslik 12", "D1012");

        CampusDTO campusDTO = new CampusDTO(null, "üsküdar kampüsü", "http://uskudarkampus.com", "02160000012", "uskudar@kampus.com", Stream.of(classroomDTO1, classroomDTO2).collect(Collectors.toSet()));

        Campus campus = CampusMapper.INSTANCE.toEntity(campusDTO);

        campusRepository.save(campus);

        return campus;
    }

    @GetMapping("/many-to-many")
    public Meeting manyToManyExample() {

        MemberDTO member1 = new MemberDTO(null, "Ali ALİOĞLU");
        MemberDTO member2 = new MemberDTO(null, "Veli VELİOĞLU");

        MeetingDTO meetingDTO = new MeetingDTO(null, "Spring boot eğitimi", new Date(), Stream.of(member1, member2).collect(Collectors.toSet()));

        Meeting meeting = MeetingMapper.INSTANCE.toEntity(meetingDTO);

        meetingRepository.save(meeting);

        return meeting;
    }

    @GetMapping("/many-to-many-dto")
    public MeetingDTO manyToManyDtoExample() {

        Member member1 = new Member(null, "Ahmet YILMAZ");
        Member member2 = new Member(null, "Can YILMAZ");

        Meeting meeting = new Meeting(null, "Değerlendirme toplantısı", new Date(), Stream.of(member1, member2).collect(Collectors.toSet()));

        meetingRepository.save(meeting);

        MeetingDTO meetingDTO = MeetingMapper.INSTANCE.toDTO(meeting);

        return meetingDTO;
    }
}
