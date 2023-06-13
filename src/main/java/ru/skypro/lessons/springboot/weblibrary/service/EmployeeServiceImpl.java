package ru.skypro.lessons.springboot.weblibrary.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.PagingRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PagingRepository pagingRepository;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
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


    public List<EmployeeDTO> getAllEmployees(){
        List<Employee> res = new ArrayList<>();
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
        logger.info("Was invoked method for create employee " + employee.toString());
    }

    @Override
    public Employee findEmpById(int id) {
        Employee result = employeeRepository.findById(id).get();
        if(result.equals(null)){
            logger.error("There is no employee with id = " + id);
            return result;
        }
        else{
            return result;
        }
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


}
