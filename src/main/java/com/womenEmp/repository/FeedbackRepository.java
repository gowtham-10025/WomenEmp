package com.womenEmp.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.womenEmp.entity.Feedback;
import com.womenEmp.entity.Scheme;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	List<Feedback> viewFeedBackBySchemeName(@Param(value = "schemeName") String schemeName);
	List<Feedback> viewFeedBackByTrainingCourseName(@Param(value = "courseName") String courseName);
}