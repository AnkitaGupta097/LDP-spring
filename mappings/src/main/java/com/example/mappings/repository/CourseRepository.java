package com.example.mappings.repository;

import com.example.mappings.entity.Course;
import com.example.mappings.entity.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Integer> {
}