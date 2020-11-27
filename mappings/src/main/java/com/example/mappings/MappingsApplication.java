package com.example.mappings;

import com.example.mappings.entity.Course;
import com.example.mappings.entity.Instructor;
import com.example.mappings.entity.InstructorDetail;
import com.example.mappings.repository.CourseRepository;
import com.example.mappings.repository.InstructorDetailRepository;
import com.example.mappings.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class MappingsApplication  {

	public static void main(String[] args) {
		SpringApplication.run(MappingsApplication.class, args);
	}

}




