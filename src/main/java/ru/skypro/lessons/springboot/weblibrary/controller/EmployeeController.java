package ru.skypro.lessons.springboot.weblibrary.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/employee")
    public List<EmployeeFullInfo> getEmployees() {
        return employeeService.getAllInfo();
    }
    @GetMapping("/{id}/fullInfo")
    public Employee findById(@PathVariable int id) {
        return employeeService.findEmpById(id);
    }

    @GetMapping("/withHighestSalary")
    public List<EmployeeDTO> findHighest() {
        return employeeService.findHighest();
    }
    @GetMapping("/")
    public List<EmployeeFullInfo> findByPos(@RequestParam("position") String position) {
        return employeeService.findByPos(position);
    }
    @GetMapping("/page")
    public List<Employee> findByPos(@RequestParam("page") int page) {
        return employeeService.getEmployeeWithPaging(page);
    }

}