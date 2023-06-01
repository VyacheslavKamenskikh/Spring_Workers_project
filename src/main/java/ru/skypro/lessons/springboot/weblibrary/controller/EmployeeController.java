package ru.skypro.lessons.springboot.weblibrary.controller;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/")
    public List<EmployeeFullInfo> getEmployees() {
        return employeeService.getAllInfo();
    }
    @GetMapping("/employee/{id}/fullInfo")
    public Employee findById(@PathVariable int id) {
        return employeeService.findEmpById(id);
    }

    @GetMapping("/employee/withHighestSalary")
    public List<EmployeeDTO> findHighest() {
        return employeeService.findHighest();
    }
    @GetMapping("/employee/")
    public List<EmployeeFullInfo> findByPos(@RequestParam("position") String position) {
        return employeeService.findByPos(position);
    }
    @GetMapping("/employee/page")
    public List<Employee> findByPos(@RequestParam("page") int page) {
        return employeeService.getEmployeeWithPaging(page);
    }
    @PostMapping("/employee/create")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PostMapping(value = "/employee/upload" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        employeeService.uploadEmployee(file);
    }

    @PostMapping("/report")
    public int addReport() throws SQLException, IOException {
        return employeeService.addReport();
    }

    @GetMapping("/report/{id}")
    public Report getReportbyId(@PathVariable("id") int id) throws SQLException {
        return employeeService.getReportbyId(id);
    }
}