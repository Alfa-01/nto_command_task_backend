package com.example.nto.service.impl;

import com.example.nto.entity.Employee;
import com.example.nto.repository.CodeRepository;
import com.example.nto.repository.EmployeeRepository;
import com.example.nto.service.CodeService;
import com.example.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeCodeServiceImpl implements EmployeeService, CodeService {

    private final EmployeeRepository employeeRepository;
    private final CodeRepository codeRepository;

    @Override
    public Employee updateEmployee(long id, Employee newEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) throw new RuntimeException("No such user with id " + id);

        Employee employee = optionalEmployee.get();
        employee.setName(newEmployee.getName());
        employee.setLogin(newEmployee.getLogin());
        employee.setPhoto(newEmployee.getPhoto());
        employee.setPosition(newEmployee.getPosition());
        employee.setLastVisit(newEmployee.getLastVisit());

        return employeeRepository.save(employee);
    }

    @Override
    public Employee findByLogin(String login) {
        if (employeeRepository.findExistByLogin(login))
            return employeeRepository.findByLogin(login);
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                "There is no account with login " + login + " or it is incorrect");
    }

    @Override
    public boolean findExistByLogin(String login) {
        if (employeeRepository.findExistByLogin(login))
            throw new ResponseStatusException(HttpStatus.OK, "Login is existing, processing");
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                "There is no account with login " + login + " or it is incorrect");

    }

    @Override
    public Employee openDoor(String login, Long value) {
        if (findByLogin(login) != null && findExistByValue(value)) {
            Employee employee = findByLogin(login);
            employee.setLastVisit(LocalDateTime.now());
            return updateEmployee(employee.getId(), employee);
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    @Override
    public Boolean findExistByValue(Long value) {
        if (codeRepository.findExistByValue(value))
            return codeRepository.findExistByValue(value);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
