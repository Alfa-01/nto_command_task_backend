package com.example.nto.service;

import com.example.nto.entity.Employee;

public interface EmployeeService {

    Employee updateEmployee(long id, Employee newEmployee);
    Employee findByLogin(String login);
    boolean findExistByLogin(String login);
}
