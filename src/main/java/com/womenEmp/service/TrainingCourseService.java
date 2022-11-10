package com.womenEmp.service;
import java.util.List;

import com.womenEmp.dto.TrainingCourseDTO;
import com.womenEmp.exception.WomenEmpException;

public interface TrainingCourseService {
	
	public TrainingCourseDTO addTrainingCourse(TrainingCourseDTO trainingCourseDTO) throws WomenEmpException;
	public TrainingCourseDTO updateTrainingCourse(TrainingCourseDTO trainingCourseDTO) throws WomenEmpException;
	public TrainingCourseDTO viewTrainingCourse(Integer CourseId) throws WomenEmpException;
	public void deleteTrainingCourse(Integer CourseId) throws WomenEmpException;
	public List<TrainingCourseDTO> viewAllTrainingCourse() throws WomenEmpException;
	public TrainingCourseDTO viewByTrainingCourseName(String CourseName) throws WomenEmpException;
	
	
	
}