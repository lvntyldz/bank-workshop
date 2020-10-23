package com.ba.controller;

import com.ba.domain.Car;
import com.ba.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("car")
public class CarController {

    @Autowired
    CarService service;

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) {
        return service.getCarById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        return service.deleteCarById(id);
    }

}
