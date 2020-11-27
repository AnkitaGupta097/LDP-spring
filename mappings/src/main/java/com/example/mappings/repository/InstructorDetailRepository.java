package com.example.mappings.repository;

import com.example.mappings.entity.Instructor;
import com.example.mappings.entity.InstructorDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorDetailRepository extends JpaRepository<InstructorDetail,Integer> {
}
