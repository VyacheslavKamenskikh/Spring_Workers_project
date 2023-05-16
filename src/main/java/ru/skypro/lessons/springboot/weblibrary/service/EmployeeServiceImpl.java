package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

    public int getSum() {
        return employeeRepository.getSum();
    }

    public Employee getMin() {
        return employeeRepository.getMin();
    }

    public Employee getMax() {
        return employeeRepository.getMax();
    }

    public List<Employee> getHighS(){
        return employeeRepository.getHighS();
    };
    public Employee getEmpById(int id) {
        return employeeRepository.getEmpById(id);
    }

    public List<Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary) {
        return employeeRepository.getEmployeesWithSalaryHigherThan(compareSalary);
    }

    @Override
    public void deleteEmpById(int id) {
        employeeRepository.getEmpById(id);
    }

    @Override
    public void createEmployee(List<Employee> employee){
        employeeRepository.createEmployee(employee);
    }

    @Override
    public void updateEmployee(int id, Employee employee) {
        employeeRepository.updateEmployee(id, employee);
    }
}
