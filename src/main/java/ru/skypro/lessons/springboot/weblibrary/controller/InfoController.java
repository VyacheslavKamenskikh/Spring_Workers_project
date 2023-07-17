package ru.skypro.lessons.springboot.weblibrary.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.lessons.springboot.weblibrary.dto.EmployeeFullInfo;

import java.util.List;

@RestController
@RequestMapping("/")
public class InfoController {
    @Value("${app.env}")
    private String url;
    @GetMapping("/appInfo")
    public String getInfo() {
        return url;
    }
}
