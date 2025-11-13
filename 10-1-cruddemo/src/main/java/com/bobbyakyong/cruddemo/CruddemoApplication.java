package com.bobbyakyong.cruddemo;

import com.bobbyakyong.cruddemo.dao.StudentDAO;
import com.bobbyakyong.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}


    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
        return runner -> {
//            createStudent(studentDAO);

            createMultipleStudents(studentDAO);

//            readStudent(studentDAO);

//            queryForStudents(studentDAO);

//            queryForStudentsWithLastName(studentDAO);

//            updateStudent(studentDAO);

//            deleteStudent(studentDAO);

//            deleteAllStudents(studentDAO);
        };

    }

    private void createStudent(StudentDAO studentDAO) {
        //create the new student object
        System.out.println("Creating the new student object");
        Student student = new Student("Paul","Doe", "paul@qeong.com");

        //save the student
        System.out.println("Saving the student");
        studentDAO.save(student);

        //display the student
        System.out.println("Saved the student. Generated Id: "+" "+student.getId());
    }

    private void createMultipleStudents(StudentDAO studentDAO) {
        Student student1 = new Student("John","Doe", "john@qeong.com");
        Student student2 = new Student("Mary","Doe", "mary@qeong.com");
        Student student3 = new Student("Bonita","Doe", "Bonita@qeong.com");
        studentDAO.save(student1);
        studentDAO.save(student2);
        studentDAO.save(student3);
    }

    private void readStudent(StudentDAO studentDAO) {
        Student student = new Student("Daffy","Duck", "daffy@qeong.com");
        studentDAO.save(student);
        int theId = student.getId();
        System.out.println("Saved the student. Generated Id: "+theId);

        Student foundStudent = studentDAO.findById(theId);
        System.out.println("Found student: "+foundStudent.toString());
    }

    private void queryForStudents(StudentDAO studentDAO){
        List<Student> students = studentDAO.findAll();
        for(Student student: students){
            System.out.println("Found student: "+student);
        }
    }

    private void queryForStudentsWithLastName(StudentDAO studentDAO){
        List<Student> students = studentDAO.findByLastName("Doe");
        for(Student student: students){
            System.out.println("Found student: "+student);
        }
    }

    private void updateStudent(StudentDAO studentDAO){
        int id  = 1;
        Student student = studentDAO.findById(id);
        student.setFirstName("Bobby");
        studentDAO.update(student);
        System.out.println("Updated student: "+student.toString());
    }

    private void deleteStudent(StudentDAO studentDAO){
        studentDAO.delete(3);
    }

    private void deleteAllStudents(StudentDAO studentDAO){
        int numberOfRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted "+numberOfRowsDeleted+" rows");
    }
//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> {
//            System.out.println("Hello World!");
//        };
//    }

}
