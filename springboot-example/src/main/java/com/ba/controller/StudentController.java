package com.ba.controller;

import com.ba.domain.Student;
import com.ba.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentRepository repository;

    @GetMapping("/add")
    public String addStudent() {

        Student student = new Student(null, "ali", "123");
        repository.save(student);
        return student.toString();
    }

    @GetMapping("/list")
    public List<Student> getAllStudent() {
        return repository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        repository.deleteById(id);
        return "ID : " + id + " öğrenci silindi";
    }


}
