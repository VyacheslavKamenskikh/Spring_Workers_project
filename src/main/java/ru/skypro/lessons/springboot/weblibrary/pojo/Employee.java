package ru.skypro.lessons.springboot.weblibrary.pojo;

public class Employee {
    private int id;
    private String name;
    private int salary;

    public Employee(int id,String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

}
