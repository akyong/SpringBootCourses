package com.bobbyakyong.Rest.API.rest;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */

import com.bobbyakyong.Rest.API.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    @PostConstruct
    public void init() {
        students = new ArrayList<>();
        students.add(new Student("Bobby", "Akyong"));
        students.add(new Student("John", "Doe"));
        students.add(new Student("Jane", "Doe"));
        students.add(new Student("Mary", "Doe"));
        students.add(new Student("Bonita", "Doe"));
    }

    @GetMapping("/students/")
    public List<Student> getStudents() {
        return students;
    }

    @RequestMapping("/students/{studentId}")
    public Student getStudentById(@PathVariable int studentId){

        if( studentId < 0 || studentId >= students.size()) throw new StudentNotFoundException("Student id not found:"+studentId);

        return students.get(studentId);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex){
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex){
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST );
    }

}
