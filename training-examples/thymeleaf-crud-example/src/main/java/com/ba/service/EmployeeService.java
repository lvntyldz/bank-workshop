package com.ba.service;

import com.ba.model.Employee;
import com.ba.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        List<Employee> result = (List<Employee>) repository.findAll();

        if (result.size() > 0) {
            return result;
        } else {
            return null;
        }
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = repository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            return null;
        }
    }

    public Employee createOrUpdateEmployee(Employee entity) {
        if (entity.getId() == null) {
            entity = repository.save(entity);

            return entity;
        } else {
            Optional<Employee> employee = repository.findById(entity.getId());

            if (employee.isPresent()) {
                Employee newEntity = employee.get();
                newEntity.setEmail(entity.getEmail());
                newEntity.setFirstName(entity.getFirstName());
                newEntity.setLastName(entity.getLastName());

                newEntity = repository.save(newEntity);

                return newEntity;
            } else {
                entity = repository.save(entity);

                return entity;
            }
        }
    }

    public void deleteEmployeeById(Long id) {
        Optional<Employee> employee = repository.findById(id);
        if (employee.isPresent()) {
            repository.deleteById(id);
            return;
        }

        System.out.println("No record found to delete!");
    }
}
