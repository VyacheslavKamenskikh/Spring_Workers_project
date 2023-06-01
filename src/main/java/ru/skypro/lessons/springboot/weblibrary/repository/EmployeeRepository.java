package ru.skypro.lessons.springboot.weblibrary.repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> findAllEmployeeFullInfo();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = p")
    List<EmployeeFullInfo> findAllEmployee();

    @Query(value = "select * from employeesdata e where salary = (select MAX(salary) from employeesdata e2)", nativeQuery = true)
    List<EmployeeDTO> findHighest();

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.dto." +
            "EmployeeFullInfo(e.name , e.salary , p.name) " +
            "FROM Employee e join fetch Position p " +
            "WHERE e.position = :position")
    List<EmployeeFullInfo> findByPos(@Param("position") String position);

    @Query("SELECT new ru.skypro.lessons.springboot.weblibrary.entity." +
            "Position(p.name) " +
            "FROM Position p")
    List<String> findAllPos();

    default void uploadEmployee(MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Employee> employeeList = objectMapper.readValue((JsonParser) file, new TypeReference<>(){});
        for(Employee emp:employeeList){
            save(emp);
        }
    }





    default int getSum(){
        int sum = 0;
        for(EmployeeFullInfo emp : findAllEmployee()){
            sum+=emp.getSalary();
        }
        return sum;
    }


     default int getMinSalary(List<EmployeeFullInfo> empls){
        EmployeeFullInfo emp = empls.stream()
                .min(Comparator.comparingInt(EmployeeFullInfo::getSalary))
                .get();
        return emp.getSalary();
    }


     default int getMaxSalary(List<EmployeeFullInfo> empls){
        EmployeeFullInfo emp = empls.stream()
                .max(Comparator.comparingInt(EmployeeFullInfo::getSalary))
                .get();
        return emp.getSalary();
    }


     default int getMedianSalary(List<EmployeeFullInfo> empls) {
        EmployeeFullInfo empmin = empls.stream()
                .min(Comparator.comparingInt(EmployeeFullInfo::getSalary))
                .get();
        EmployeeFullInfo empmax = empls.stream()
                .max(Comparator.comparingInt(EmployeeFullInfo::getSalary))
                .get();
        int median = (empmin.getSalary()+empmax.getSalary())/2;
        return median;
    }

    default ArrayList<Report> createReport(){
        ArrayList<Report> reports = new ArrayList<>();
        ArrayList<String> pnames = new ArrayList<>();
        for(EmployeeFullInfo e : findAllEmployee()){
            pnames.add(e.getPositionName());
        }
        for (String p: pnames){
            List<EmployeeFullInfo> empls =  findByPos(p);
            int numEmp = empls.size();
            int maxSal = getMaxSalary(empls);
            int minSal = getMinSalary(empls);
            int medianSal = getMedianSalary(empls);
            reports.add(new Report(p,numEmp,maxSal,minSal,medianSal));
        }

        return reports;

    }


     default int addReport() throws SQLException, IOException {
        final String user = "postgres";
        final String password = "slavakakraft228";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final Connection connection =
                DriverManager.getConnection(url, user, password);
        List<Report> reports = createReport();
        ObjectMapper objectMapper = new ObjectMapper();
        for(Report report: reports){
            String insertQuery = "INSERT INTO report(name, numEmpl, maxSal, minSal, medSal ) VALUES (?, ?, ?, ?,?)";
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1,report.getName());
            statement.setInt(2,report.getNumEmpl());
            statement.setInt(3,report.getMaxSal());
            statement.setInt(4,report.getMinSal());
            statement.setInt(5,report.getMedSal());
            statement.executeUpdate();
        }
        String json = objectMapper.writeValueAsString(reports);
        Path path = Paths.get("file.txt");
        Files.write(path, json.getBytes(StandardCharsets.UTF_8));
        String idQuery = "SELECT LAST_INSERTED_ID FROM report";
        PreparedStatement statement = connection.prepareStatement(idQuery);
        return statement.executeQuery().getInt("id");
    }

    default Report getReportbyId(int id) throws SQLException {
        final String user = "postgres";
        final String password = "slavakakraft228";
        final String url = "jdbc:postgresql://localhost:5432/skypro";
        final Connection connection =
                DriverManager.getConnection(url, user, password);

        String insertQuery = "select * from report where id = ?";
        PreparedStatement statement = connection.prepareStatement(insertQuery);
        statement.setInt(1,id);
        ResultSet res = statement.executeQuery();
        Report rep = new Report(res.getString("name"),res.getInt("numEmpl"),res.getInt("maxSal"),res.getInt("minSal"),res.getInt("medSal"));
        return rep;
    }

}
