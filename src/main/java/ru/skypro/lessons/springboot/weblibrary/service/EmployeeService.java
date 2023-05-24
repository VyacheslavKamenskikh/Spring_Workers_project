package ru.skypro.lessons.springboot.weblibrary.service;

import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.List;

public interface EmployeeService {

   List<Employee> getEmployeeWithPaging(int page);

   List<EmployeeDTO> getAllEmployees();

   List<EmployeeFullInfo> getAllInfo();

   void addEmployee(Employee employee);

   Employee findEmpById(int id);

   List<EmployeeDTO> findHighest();

   List<EmployeeFullInfo> findByPos(String position);




   /*
    int getSum();
    Employee getMin();
    Employee getMax();
    List<Employee> getHighS();

    Employee getEmpById(int id);
    List<Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary);

    void deleteEmpById(int id);


    void createEmployee(List<Employee> employee);

    void updateEmployee(int id, Employee employee);*/
}
