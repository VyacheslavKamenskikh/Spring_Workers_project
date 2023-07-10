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

    // Геттеры и сеттеры
}
