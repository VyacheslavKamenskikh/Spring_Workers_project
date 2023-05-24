package ru.skypro.lessons.springboot.weblibrary.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.pojo.Employee;

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

   /* public List<Employee> getAllEmployees();
    public int getSum();
    Employee getMin();
    Employee getMax();

    public List<Employee> getHighS();

    Employee getEmpById(int id);

    List<Employee> getEmployeesWithSalaryHigherThan(Integer compareSalary);

    void deleteEmpById(int id);

    void createEmployee(List<Employee> employee);

    void updateEmployee(int id, Employee employee);*/
}
