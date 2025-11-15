package com.bobbyakyong.cruddemo.DAO;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */

import com.bobbyakyong.cruddemo.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
public interface EmployeeDAO {
    List<Employee> findAll();
//    Employee findById(int id);
//    void save(Employee employee);
//    void deleteById(int id);
}
