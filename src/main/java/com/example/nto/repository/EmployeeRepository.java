package com.example.nto.repository;

import com.example.nto.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("select e from Employee e where e.login = ?1")
    Employee findByLogin(String login);

    @Query("select count(e) = 1 from Employee e where login = ?1")
    Boolean findExistByLogin(String login);
}
