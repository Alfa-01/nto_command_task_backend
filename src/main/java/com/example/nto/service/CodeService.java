package com.example.nto.service;


import com.example.nto.entity.Employee;

public interface CodeService {

    Boolean findExistByValue(Long value);
    Employee openDoor(String login, Long value);
}
