package ru.skypro.lessons.springboot.weblibrary.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "report")
@NoArgsConstructor
@Getter
@Setter
public class Report implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private int numEmpl;
    private int maxSal;
    private int minSal;
    private int medSal;

    public Report(String p, int numEmp, int maxSal, int minSal, int medianSal) {
        this.name = p;
        this.numEmpl = numEmp;
        this.maxSal = maxSal;
        this.minSal = minSal;
        this.medSal = medianSal;
    }

    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", numEmpl=" + numEmpl+ '\'' +
                ", maxSal=" + maxSal+ '\'' +
                ", minSal=" + minSal+ '\'' +
                ", medSal=" + medSal +
                '}';
    }
}

