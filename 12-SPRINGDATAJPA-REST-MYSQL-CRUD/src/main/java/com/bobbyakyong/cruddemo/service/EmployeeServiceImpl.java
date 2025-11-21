package com.bobbyakyong.cruddemo.service;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */

import com.bobbyakyong.cruddemo.DAO.EmployeeRepository;
import com.bobbyakyong.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {

        //cara 1
//        Optional<Employee> employee = employeeRepository.findById(id);
//        Employee dbEmployee = null;
//        if(employee.isPresent()) {
//            dbEmployee = employee.get();
//        }
//        else{
//            throw new RuntimeException("Employee id not found: "+id);
//        }
//        return dbEmployee;

        //cara 2
//         Optional<Employee> employee = employeeRepository.findById(id);
//         Employee dbEmployee = employee.orElse(null);
//        return dbEmployee;

        //cara 3
//        return employeeRepository.findById(id).orElse(null);

        //cara 4
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee id not found: "+id));
    }

    /**
     * @Transactional using spring data jpa no need to use this annotation
     * it has been done automatically at JpaRepository level
     */
//    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

//    @Transactional
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }

}
