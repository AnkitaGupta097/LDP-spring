package com.example.mappings.Service;

import com.example.mappings.entity.Instructor;
import com.example.mappings.entity.InstructorDetail;
import com.example.mappings.repository.InstructorDetailRepository;
import com.example.mappings.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneToOneService {
    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private InstructorDetailRepository instructorDetailRepository;

    public void create(){
        Instructor tempInstructor =
				new Instructor("Madhu", "Patel", "abc@gmail.com");

		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://www.youtube.com",
				"Guitar");

		tempInstructor.setInstructorDetail(tempInstructorDetail);
		instructorRepository.save(tempInstructor);


		Instructor instructor2 =
				new Instructor("abc", "def", "def@gmail.com");

		InstructorDetail instructorDetail2 = new InstructorDetail(
				"http://www.youtube.com",
				"Singing");

		instructorDetail2.setInstructor(instructor2);
		instructorDetailRepository.save(instructorDetail2);
    }

    public void get() {

        int theId = 1;
        InstructorDetail instructorDetail = instructorDetailRepository.findById(theId).get();

        // print  the associated instructor
        System.out.println("the associated instructor: " + instructorDetail.getInstructor());

        Instructor instructor = instructorRepository.findById(theId).get();

        // print  the associated instructor
        System.out.println("the associated instructor details: " + instructor.getInstructorDetail());
    }
   public void delete (){

        int theId = 1;
        instructorDetailRepository.deleteById(theId);
        theId = 2;
        instructorRepository.deleteById(theId);

    }


}
