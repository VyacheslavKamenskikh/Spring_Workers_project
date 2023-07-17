package ru.skypro.lessons.springboot.weblibrary.repository;

import org.checkerframework.checker.lock.qual.EnsuresLockHeld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Position;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootTest
public class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Test
    void getAllEmployeesByNameTest(){
        final String targetPos="Test";
        List<EmployeeFullInfo> employeeList = employeeRepository.findByPos(targetPos);
        Assertions.assertTrue(employeeList.isEmpty());
        int expCount = 2;
        List<Employee> employeeListForSave = getEmployeeListForSave(expCount, targetPos);
        employeeRepository.saveAll(employeeListForSave);

        employeeList =employeeRepository.findByPos(targetPos);
        Assertions.assertEquals(expCount, employeeList.size());


        employeeRepository.deleteAll(employeeListForSave);
    }

    private List<Employee> getEmployeeListForSave(int count, String pos){
        List<Employee> result = Stream.generate(()->
                         new Employee(new Position(pos), new Random().nextInt()*1000))
                .limit(count*2)
                .toList();
        result.stream().limit(count).forEach(employee -> employee.setSalary(1));
        return result;
    }
}
