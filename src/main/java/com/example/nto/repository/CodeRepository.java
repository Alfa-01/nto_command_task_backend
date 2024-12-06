package com.example.nto.repository;

import com.example.nto.entity.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    @Query("select count(e) = 1 from Code e where value = ?1")
    boolean findExistByValue(Long value);
}
