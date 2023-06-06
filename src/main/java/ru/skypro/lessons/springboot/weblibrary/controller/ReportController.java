package ru.skypro.lessons.springboot.weblibrary.controller;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeDTO;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;
import ru.skypro.lessons.springboot.weblibrary.entity.Employee;
import ru.skypro.lessons.springboot.weblibrary.entity.Report;
import ru.skypro.lessons.springboot.weblibrary.repository.EmployeeRepository;
import ru.skypro.lessons.springboot.weblibrary.repository.ReportRepository;
import ru.skypro.lessons.springboot.weblibrary.service.EmployeeService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@RequestMapping("/report")
public class ReportController {
    ReportRepository reportRepository;
    @PostMapping
    public void addReport(@RequestBody Report report) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        reportRepository.save(report);
        String json = objectMapper.writeValueAsString(report);
        Path path = Paths.get("file.txt");
        try {
            Files.write(path, json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @GetMapping("/{id}")
    public Report findById(@PathVariable int id) {
        return reportRepository.findById(id).get();
    }

}
