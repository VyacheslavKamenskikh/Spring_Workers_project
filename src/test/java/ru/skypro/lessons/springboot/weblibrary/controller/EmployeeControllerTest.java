package ru.skypro.lessons.springboot.weblibrary.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;

import java.util.Random;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.web.servlet.function.ServerResponse.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    private static String EMPLOYEES_URL = "/employees";
    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @BeforeEach
    void cleanDate(){
        employeeRepository.deleteAll();
    }

    @SneakyThrows
    @Test
    void addEmployeeAndGetEmployeeByPosTest(){
        final String pos = "Test";
        EmployeeFullInfo employee = new EmployeeFullInfo();
        employee.setPositionName(pos);
        employee.setSalary(new Random().nextInt()*1000);
        String jsonEmployee = objectMapper.writeValueAsString(employee);

        mockMvc.perform(post(EMPLOYEES_URL).contentType(MediaType.APPLICATION_JSON).content(jsonEmployee)).andExpect(status().isOk());
        mockMvc.perform(get(EMPLOYEES_URL).param("position", pos)).andExpect(jsonPath("$[0].position").);
    }

    @SneakyThrows
    private String getEmployeeAsJsonString(String pos){
        EmployeeFullInfo employee = new EmployeeFullInfo();
        employee.setPositionName(pos);
        employee.setSalary(new Random().nextInt()*1000);
        return objectMapper.writeValueAsString(employee);
    }
}
