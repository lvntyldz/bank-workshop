package com.ba.service;

import com.ba.domain.Employee;
import com.ba.domain.FullTimeEmployee;
import com.ba.domain.PartTimeEmployee;
import com.ba.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public String addNewPartTimeEmployee() {
        PartTimeEmployee employee = new PartTimeEmployee("Ahmet", "YILMAZ", 30);

        return employeeRepository.save(employee).toString();
    }

    public String addFullTimeEmployee() {
        FullTimeEmployee employee = new FullTimeEmployee("Can", "YILMAZ", 500);

        employeeRepository.save(employee);

        return employee.toString();
    }

    public List<Employee> findAllEmployeeList() {
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        if (!optionalEmployee.isPresent()) {
            return null;
        }

        return optionalEmployee.get();
    }

    public String deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
        return "ID : " + id + " olan içerik silindi";
    }
}
