package com.bobbyakyong.cruddemo.DAO;

import com.bobbyakyong.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
