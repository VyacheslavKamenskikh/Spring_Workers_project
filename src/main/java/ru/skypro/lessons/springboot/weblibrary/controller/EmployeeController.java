package ru.skypro.lessons.springboot.weblibrary.controller;


import org.springframework.web.bind.annotation.*;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
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
    @PostMapping("/create")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }
    /*
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
    @GetMapping("/{id}")
    public Employee getEmpById(@PathVariable int id) {
            return employeeService.getEmpById(id);
    }
    @GetMapping("/salaryHigherThan")
    public List<Employee> getEmployeesWithSalaryHigherThan(@RequestParam("salary") Integer compareSalary) {
        return employeeService.getEmployeesWithSalaryHigherThan(compareSalary);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeesWithSalaryHigherThan(@PathVariable int id) {
        employeeService.deleteEmpById(id);
    }

    @PostMapping("/")
    public void addEmployee(@RequestBody List<Employee> employee) {
        employeeService.createEmployee(employee);
    }

    @PostMapping("/{id}")
    public void updateEmployee(@PathVariable int id,@RequestBody Employee employee) {
        employeeService.updateEmployee(id,employee);
    }*/
}