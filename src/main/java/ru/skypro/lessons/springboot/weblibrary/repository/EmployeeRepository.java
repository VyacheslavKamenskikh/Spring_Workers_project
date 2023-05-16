package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    public List<Employee> getAllEmployees();
    public int getSum();
    Employee getMin();
    Employee getMax();

    public List<Employee> getHighS();

    Employee getEmpById(int id);

    List<Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary);

    void deleteEmpById(int id);

    void createEmployee(List<Employee> employee);

    void updateEmployee(int id, Employee employee);
}
