package com.ba.service;

import com.ba.domain.Employee;
import com.ba.domain.PartTimeEmployee;
import com.ba.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository employeeRepository;

    private PartTimeEmployee employee;

    @Before
    public void setup() {
        employee = new PartTimeEmployee();
        employee.setHourlyRate(100);
        employee.setFirstName("Ali");
        employee.setLastName("ALİOĞLU");
        employee.setId(123L);
    }

    @Test
    public void shouldAddNewEmployee() {

        when(employeeRepository.save(any())).thenReturn(employee);

        String res = service.addNewPartTimeEmployee();

        verify(employeeRepository, times(1)).save(any(PartTimeEmployee.class));
        assertNotNull(res);
        assertEquals(res, employee.toString());
    }

    @Test
    public void shouldFindEmployeeById() {

        Long id = 123L;

        when(employeeRepository.findById(id)).thenReturn(Optional.of(employee));

        Employee employee2 = service.findEmployeeById(id);
        assertNotNull(employee2);
        assertEquals(employee2.getId(), employee.getId());
    }

    @Test
    public void shouldNotFindEmployeeByIdWhenEmployeeNotExist() {

        Long id = 123L;

        when(employeeRepository.findById(id)).thenReturn(Optional.empty());

        Employee employee2 = service.findEmployeeById(id);
        assertNull(employee2);
    }

    @Test
    public void shouldDeleteEmployeeById() {
        Long id = 111L;

        String res = service.deleteEmployeeById(id);
        assertNotNull(res);
        assertEquals(res, "ID : " + id + " olan içerik silindi");
    }

    @Test(expected = Exception.class)
    public void shouldNotDeleteEmployeeWhenExceptionThrown() {
        Long id = 111L;

        doThrow(new RuntimeException("Burayı silemezsin")).when(employeeRepository).deleteById(id);

        service.deleteEmployeeById(id);
    }

    @Test
    public void shouldNotDeleteEmployeeWhenExceptionThrown2() {
        Long id = 111L;
        String errorMsg = "Burayı silemezsin";

        doThrow(new RuntimeException(errorMsg)).when(employeeRepository).deleteById(id);

        try {
            service.deleteEmployeeById(id);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), errorMsg);
        }
    }
}
