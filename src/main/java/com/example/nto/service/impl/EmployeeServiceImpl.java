package com.example.nto.service.impl;

import com.example.nto.entity.Code;
import com.example.nto.entity.Employee;
import com.example.nto.repository.CodeRepository;
import com.example.nto.repository.EmployeeRepository;
import com.example.nto.service.CodeService;
import com.example.nto.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, CodeService {

    private final EmployeeRepository employeeRepository;
    private final CodeRepository codeRepository;

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
        long employeeId = findByLogin(login).getId();

        Optional<Code> codeOptional = codeRepository.findById(employeeId);
        if (codeOptional.isEmpty()) throw new RuntimeException("Code with id " + employeeId + "is not found");

        Code code = codeOptional.get();
        code.setValue(newCode.getValue());

        return codeRepository.save(code);
    }

}
