package ru.skypro.lessons.springboot.weblibrary.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.PagingRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PagingRepository pagingRepository;
    // Создаем конструктор, который принимает репозиторий сотрудников
    // Чтобы внедрить зависимость репозитория сотрудников в данный сервис
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,PagingRepository pagingRepository) {
        this.employeeRepository = employeeRepository;
         this.pagingRepository = pagingRepository;
    }



    @Override
    public List<Employee> getEmployeeWithPaging(int page1) {
        Pageable employeeOfConcretePage = PageRequest.of(page1, 10);
        Page<Employee> page = (Page<Employee>) employeeRepository.findAll();

        return page.stream()
                .toList();
    }

    List<Employee> res = new ArrayList<>();
    List<Employee> res2 = new ArrayList<>();
    public List<EmployeeDTO> getAllEmployees(){
        employeeRepository.findAll().forEach(res::add);
        return res.stream().map(EmployeeDTO::fromEmployee).toList();
    }

    @Override
    public List<EmployeeFullInfo> getAllInfo(){
        return employeeRepository.findAllEmployeeFullInfo();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public Employee findEmpById(int id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public List<EmployeeDTO> findHighest() {
        return employeeRepository.findHighest();
    }

    @Override
    public List<EmployeeFullInfo> findByPos(String position) {
        return employeeRepository.findByPos(position);
    }

    @Override
    public void uploadEmployee(MultipartFile file) throws IOException {
        employeeRepository.uploadEmployee(file);
    }

    @Override
    public int addReport() throws SQLException, IOException {
        return employeeRepository.addReport();
    }

    @Override
    public Report getReportbyId(int id) throws SQLException {
        return employeeRepository.getReportbyId(id);
    }


}
