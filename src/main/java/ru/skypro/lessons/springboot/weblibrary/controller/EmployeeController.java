package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> showCounter() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/salary/sum")
    public int showSum() {
        return employeeService.getSum();
    }

    @GetMapping("/salary/min")
    public Employee getMin() {
        return employeeService.getMin();
    }
    @GetMapping("/salary/max")
    public Employee getMax() {
        return employeeService.getMax();
    }
    @GetMapping("/high-salary")
    public List<Employee> getHighS() {
        return employeeService.getHighS();
    }
}