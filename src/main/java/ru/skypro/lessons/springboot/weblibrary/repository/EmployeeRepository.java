package ru.skypro.lessons.springboot.weblibrary.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;

import java.io.IOException;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> findAllEmployeeFullInfo();

    @Query(value = "select * from employeesdata e where salary = (select MAX(salary) from employeesdata e2)", nativeQuery = true)
    List<EmployeeDTO> findHighest();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = :position")
    List<EmployeeFullInfo> findByPos(@Param("position") String position);

    default void uploadEmployee(MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employeeList = objectMapper.readValue((JsonParser) file, new TypeReference<>(){});
        for(Employee emp:employeeList){
            save(emp);
        }
    }
}
