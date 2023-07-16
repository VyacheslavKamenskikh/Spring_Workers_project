package ru.skypro.lessons.springboot.weblibrary.dto;

import lombok.Data;

@Data
public class EmployeeFullInfo {
    // Имя сотрудника
    private String name;
    // Зарплата сотрудника
    private Integer salary;
    // Название должности сотрудника
    private String positionName;


    // Конструктор класса EmployeeFullInfo
    public EmployeeFullInfo(String name, Integer salary, String positionName) {
        this.name = name;
        this.salary = salary;
        this.positionName = positionName;
    }

    public EmployeeFullInfo() {

    }

    public EmployeeFullInfo(String pos, int i) {
        this.positionName = pos;
        this.salary = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    // Геттеры и сеттеры
}
