package com.ba.controller;

import com.ba.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("person")
public class PersonController {

    @Autowired
    private PersonService service;

    @GetMapping("/add")
    public String addPerson() {
        return service.addNewPerson();
    }

    @DeleteMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        return service.deletePersonById(id);
    }
}
