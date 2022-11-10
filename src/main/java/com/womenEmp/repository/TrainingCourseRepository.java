package com.womenEmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.womenEmp.entity.TrainingCourse;

public interface TrainingCourseRepository extends JpaRepository<TrainingCourse, Integer> {
	TrainingCourse findByCourseName(String courseName);
}