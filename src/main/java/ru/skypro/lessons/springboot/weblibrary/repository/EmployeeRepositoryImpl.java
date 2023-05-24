package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.stereotype.Repository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Repository
public class EmployeeRepositoryImpl{

   /* private List<Employee> employeeList = List.of(
            new Employee(1,"Катя", 90_000),
            new Employee(2,"Дима", 160_000),
            new Employee(3,"Олег", 80_000),
            new Employee(4,"Вика", 165_000));

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

    @Override
    public Employee getEmpById(int id){
        for(Employee empl : employeeList) {
            if(empl.getId()==(id)) {
                return empl;
            }
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary){
        List<Employee> empList = new ArrayList<>();
        for(Employee emp : employeeList){
            if(emp.getSalary()>compareSalary){
                empList.add(emp);
            }
        }
        return empList;
    }

    @Override
    public void deleteEmpById(int id){
        Employee delEmpl = null;
        for(Employee empl : employeeList) {
            if(empl.getId()==(id)) {
                delEmpl = empl;
            }
        }
        employeeList.remove(delEmpl);
    }

    @Override
    public void createEmployee(List<Employee> employee){
        employeeList.addAll(employee);
    }

    @Override
    public void updateEmployee(int id, Employee employee){
        int i = 0;
        for(Employee empl : employeeList) {
            if(empl.getId()==(id)) {
                employeeList.set(i,employee);
            }
            i++;
        }
    }*/
}
