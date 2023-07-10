package ru.skypro.lessons.springboot.weblibrary;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

@SpringBootTest
public class WebLibraryApplicationTest {
   @Autowired
   private EmployeeService employeeService;
    @Test
    void contextLoads(){
        Assertions.assertNotNull(employeeService);
    }
}
