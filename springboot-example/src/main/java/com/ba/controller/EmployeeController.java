package com.ba.controller;

import com.ba.domain.Employee;
import com.ba.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/part-time/add")
    public String addPartTimeEmployee() {
        return service.addNewPartTimeEmployee();
    }

    @GetMapping("/full-time/add")
    public String addFullTimeEmployee() {
        return service.addFullTimeEmployee();
    }

    @GetMapping("/list")
    public List<Employee> getAllEmployee() {
        return service.findAllEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        return service.findEmployeeById(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return service.deleteEmployeeById(id);
    }
}
