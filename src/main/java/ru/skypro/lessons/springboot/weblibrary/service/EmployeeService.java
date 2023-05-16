package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    int getSum();
    Employee getMin();
    Employee getMax();
    List<Employee> getHighS();

    Employee getEmpById(int id);
    List<Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary);

    void deleteEmpById(int id);


    void createEmployee(List<Employee> employee);

    void updateEmployee(int id, Employee employee);
}
