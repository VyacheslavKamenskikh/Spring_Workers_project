package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.repository.CrudRepository;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;

public interface ReportRepository extends CrudRepository<Report, Integer> {

}
