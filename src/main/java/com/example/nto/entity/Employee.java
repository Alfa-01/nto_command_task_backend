package com.example.nto.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private long id;
    private String login;
    private String name;
    private String photo;
    private String position;
    private LocalDateTime lastVisit;
}
