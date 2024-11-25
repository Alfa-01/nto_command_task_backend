package com.example.nto.service;

import com.example.nto.entity.Employee;

public interface EmployeeService {

    Employee findByLogin(String login);
    Boolean findExistByLogin(String login);
}
