package com.bobbyakyong.cruddemo.dao;

import com.bobbyakyong.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */

@Repository
public class StudentDAOImpl implements StudentDAO{

    // define field for entityManager
    private EntityManager entityManager;

    // inject entityManager using constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    //implement methods for save
    @Override
    @Transactional
    public void save(Student student) {
        this.entityManager.persist(student);
        System.out.println("Saving student: "+student.toString());
    }

    @Override
    public Student findById(Integer id) {
        return this.entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = entityManager.createQuery("FROM Student s order by lastName desc", Student.class);
        return query.getResultList();
    }

    public List<Student> findByLastName(String lastName){
        TypedQuery<Student> query = entityManager.createQuery("FROM Student s where s.lastName = :lastName", Student.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void update(Student student){
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id){
        Student student = entityManager.find(Student.class, id);
        entityManager.remove(student);
    }
    @Override
    @Transactional
    public int deleteAll(){
        int numberOfRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return numberOfRowsDeleted;
    }
}
