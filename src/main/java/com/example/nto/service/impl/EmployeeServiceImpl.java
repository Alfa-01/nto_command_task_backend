package com.example.nto.service.impl;

import com.example.nto.entity.Code;
import com.example.nto.entity.Employee;
import com.example.nto.repository.CodeRepository;
import com.example.nto.repository.EmployeeRepository;
import com.example.nto.service.CodeService;
import com.example.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, CodeService {

    private final EmployeeRepository employeeRepository;
    private final CodeRepository codeRepository;

    @Override
    public Employee updateEmployee(long id, Employee newEmployee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) throw new RuntimeException("No such user with id" + id);

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
        return employeeRepository.findByLogin(login);
    }

    @Override
    public Boolean findExistByLogin(String login) {
        return employeeRepository.findExistByLogin(login);
    }

    @Override
    public Code update(String login, Code newCode) {
        Employee employee = findByLogin(login);
        long employeeId = employee.getId();

        employee.setLastVisit(LocalDateTime.now());
        updateEmployee(employeeId, employee);

        Optional<Code> codeOptional = codeRepository.findById(employeeId);
        if (codeOptional.isEmpty()) throw new RuntimeException("Code with id " + employeeId + "is not found");

        Code code = codeOptional.get();
        code.setValue(newCode.getValue());

        return codeRepository.save(code);
    }

}
