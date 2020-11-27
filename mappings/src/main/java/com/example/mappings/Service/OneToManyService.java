package com.example.mappings.Service;

import com.example.mappings.entity.Course;
import com.example.mappings.entity.Instructor;
import com.example.mappings.repository.CourseRepository;
import com.example.mappings.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneToManyService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private CourseRepository courseRepository;

    public void create(){

        int	 theId = 2;
        Instructor instructorToUpdate= instructorRepository.findById(theId).get();
        Course tempCourse1 = new Course("Duck training - volume 1");
        Course tempCourse2 = new Course("Duck training - volume 2");

        // add courses to instructor with id 2

        tempCourse1.setInstructor(instructorToUpdate);
        tempCourse2.setInstructor(instructorToUpdate);

        courseRepository.save(tempCourse1);
        courseRepository.save(tempCourse2);

// add courses in to instructor with id 1
        theId = 1;
        instructorToUpdate= instructorRepository.findById(theId).get();
        Course tempCourse3 = new Course("singing course 1");
        Course tempCourse4 = new Course("singing course 2");

        instructorToUpdate.add(tempCourse3);
        instructorToUpdate.add(tempCourse4);

        instructorRepository.save(instructorToUpdate);


    }
    public void delete(){

        //deleting course with id 1
        int theId = 1;

        // delete course with id 1
        courseRepository.deleteById(theId);

    }

    public void get(){
        int theId = 2;

        // get courses for the instructor with id
        Instructor tempInstructor= instructorRepository.findById(theId).get();
        System.out.println("\nCourses with instructor id:" +theId + tempInstructor.getCourses());

        //get instructor of course id 2
        Course course= courseRepository.findById(theId).get();

        System.out.println("\n instructor of Course id:"+theId + course.getInstructor());

    }


}
