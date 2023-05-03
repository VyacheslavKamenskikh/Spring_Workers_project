package ru.skypro.lessons.springboot.weblibrary.repository;

import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeRepository {
    public List<Employee> getAllEmployees();
    public int getSum();
    Employee getMin();
    Employee getMax();

    public List<Employee> getHighS();
}
