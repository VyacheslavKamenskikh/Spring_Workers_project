package ru.skypro.lessons.springboot.weblibrary.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Position;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    void addEmployee_CorrectEmployee_ShouldCallRep() {
        Employee employee = new Employee();
        employee.setId(2000);
        employee.setName("nnnnn");
        employee.setSalary(20000000);
        employeeService.addEmployee(employee);
        Mockito.verify(employeeRepository, Mockito.times(1)).save(employee);
    }

    @Test
    void findEmpById_ValidId_ShouldReturnEmployee() {
        Employee expected = new Employee();
        expected.setId(2000);
        expected.setName("nnnnn");
        expected.setSalary(20000000);

        int input = 2000;

        Mockito.when(employeeRepository.findById(input)).thenReturn(Optional.of(expected));

        Employee actual = employeeService.findEmpById(input);

        assertEquals(expected,actual);
        Mockito.verify(employeeRepository, Mockito.times(1)).findById(input);
    }


    @Test
    void findByPos() {
        Employee expected = new Employee();
        expected.setId(2000);
        expected.setName("nnnnn");
        expected.setSalary(20000000);
        expected.setPosition(new Position(2000,"test"));

        Position input = new Position(2000,"test");

        Mockito.when(employeeRepository.findByPos(input.getName())).thenReturn((List<EmployeeFullInfo>) expected);

        List<EmployeeFullInfo> actual =  employeeService.findByPos(input.getName());

        assertEquals(expected,actual);
        Mockito.verify(employeeRepository, Mockito.times(1)).findByPos(input.getName());
    }

}