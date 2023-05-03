package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository{
    private final List<Employee> employeeList = List.of(
            new Employee("Катя", 90_000),
            new Employee("Дима", 160_000),
            new Employee("Олег", 80_000),
            new Employee("Вика", 165_000));

    @Override
    public List<Employee> getAllEmployees() {
        return employeeList;
    }

    @Override
    public int getSum(){
        int sum = 0;
        for(Employee emp : employeeList){
            sum+=emp.getSalary();
        }
        return sum;
    }

    @Override
    public Employee getMin(){
        Employee emp = employeeList.stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .get();
        return emp;
    }

    @Override
    public Employee getMax(){
        Employee emp = employeeList.stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
        return emp;
    }

    @Override
    public List<Employee> getHighS() {
        Employee empmin = employeeList.stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .get();
        Employee empmax = employeeList.stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
        int median = (empmin.getSalary()+empmax.getSalary())/2;
        List<Employee> medianList = new ArrayList<>();
        for(Employee emp : employeeList){
            if(emp.getSalary()>median){
                medianList.add(emp);
            }
        }
        return medianList;
    }
}
