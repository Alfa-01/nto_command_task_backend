package com.example.nto.controller;

import com.example.nto.entity.Employee;
import com.example.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("api/{login}/info")
    public Employee findByLogin(@PathVariable String login) {
        return employeeService.findByLogin(login);
    }

    @GetMapping("api/{login}/auth")
    public void findExistByLogin(@PathVariable String login) {
        employeeService.findExistByLogin(login);
    }
}
