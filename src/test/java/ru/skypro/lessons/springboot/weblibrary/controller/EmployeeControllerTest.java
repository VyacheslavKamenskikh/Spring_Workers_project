package ru.skypro.lessons.springboot.weblibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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

    @Autowired
    ObjectMapper objectMapper;

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

        mockMvc.perform(post(EMPLOYEES_URL).contentType(MediaType.APPLICATION_JSON).content(jsonEmployee)).andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(get(EMPLOYEES_URL).param("position", pos)).andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$[0].position"));
    }

    @SneakyThrows
    private String getEmployeeAsJsonString(String pos){
        EmployeeFullInfo employee = new EmployeeFullInfo();
        employee.setPositionName(pos);
        employee.setSalary(new Random().nextInt()*1000);
        return objectMapper.writeValueAsString(employee);
    }

    @SneakyThrows
    @Test
    void addEmployeeTest(){
        EmployeeFullInfo employee = new EmployeeFullInfo();
        employee.setSalary(new Random().nextInt()*1000);
        String jsonEmployee =   objectMapper.writeValueAsString(employee);
        mockMvc.perform(post(EMPLOYEES_URL).contentType(MediaType.APPLICATION_JSON).content(jsonEmployee)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @SneakyThrows
    @Test
    void GetEmployeesTest(){
        mockMvc.perform(get(EMPLOYEES_URL)).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @SneakyThrows
    @Test
    void findHighestTest(){
        final int sal = 5000;
        EmployeeFullInfo employee = new EmployeeFullInfo();
        employee.setPositionName("test1");
        employee.setSalary(sal);
        EmployeeFullInfo employee2 = new EmployeeFullInfo();
        employee.setPositionName("test2");
        employee.setSalary(2000);

        String jsonEmployee = objectMapper.writeValueAsString(employee);
        String jsonEmployee2 = objectMapper.writeValueAsString(employee2);
        mockMvc.perform(post(EMPLOYEES_URL).contentType(MediaType.APPLICATION_JSON).content(jsonEmployee)).andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(post(EMPLOYEES_URL).contentType(MediaType.APPLICATION_JSON).content(jsonEmployee2)).andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(get(EMPLOYEES_URL+"/withHighestSalary")).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$[0].salary").isArray()).andExpect((ResultMatcher) MockMvcResultMatchers.jsonPath("$", jsonEmployee));
    }

    @SneakyThrows
    @Test
    void findByPage(){
        final int fpage = 1;
        mockMvc.perform(get(EMPLOYEES_URL+"/page").param("page", String.valueOf(fpage))).andExpect(MockMvcResultMatchers.status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }
}
