package com.example.mappings.Service;

import com.example.mappings.entity.Course;
import com.example.mappings.entity.Instructor;
import com.example.mappings.entity.Student;
import com.example.mappings.repository.CourseRepository;
import com.example.mappings.repository.InstructorRepository;
import com.example.mappings.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManyToManyService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void create(){
     Student student1= new Student("ankita","gupta","abc@gmail.com");
     Student student2= new Student("john","surname","def@gmail.com");


        Course tempCourse1 = courseRepository.findById(3).get();

        Course tempCourse2 = courseRepository.findById(4).get();


        // add students to course with id 3
        tempCourse1.addStudent(student1);
        tempCourse1.addStudent(student2);

        courseRepository.save(tempCourse1);

        //add student to course with id 4
        Student student= studentRepository.findById(1).get();
        tempCourse2.addStudent(student);
        courseRepository.save(tempCourse2);


    }
    public void delete(){
        //delete student with id 2 but it will not delete corresponding courses
        int theId=2;
        studentRepository.deleteById(theId);

    }

    public void get(){

        int theId=1;
        //get courses of student with id 1
       Student student= studentRepository.findById(theId).get();
        System.out.println("\n all courses of student id"+theId +student.getCourses() );

    }

}
