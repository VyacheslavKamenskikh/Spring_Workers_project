package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

public interface PagingRepository extends PagingAndSortingRepository<Employee, Integer> {
}
