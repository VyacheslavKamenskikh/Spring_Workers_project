package ru.skypro.lessons.springboot.weblibrary.dto;

import lombok.*;
import lombok.experimental.Accessors;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

@Data
@AllArgsConstructor
@Accessors(chain = true)
@NoArgsConstructor
public class EmployeeDTO {
    private String name;
    private Integer salary;

    public static EmployeeDTO fromEmployee(Employee employee) {
        return new EmployeeDTO().setName(employee.getName()).setSalary(employee.getSalary());
    }

    // Метод для преобразования объекта EmployeeDTO в сущность Employee
    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setName(this.getName());
        employee.setSalary(this.getSalary());
        return employee;
    }
}
