package com.womenEmp.controller;


import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.womenEmp.dto.TrainingCourseDTO;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.service.TrainingCourseService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class TrainingCourseAPI {
	public static final Log LOGGER = LogFactory.getLog(TrainingCourseAPI.class);
	
	@Autowired
	TrainingCourseService trainingCourseService;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/TrainingCourse")
	public ResponseEntity<TrainingCourseDTO> addTrainingCourse(@Valid @RequestBody TrainingCourseDTO trainingCourseDTO) throws WomenEmpException{
		TrainingCourseDTO ret =  trainingCourseService.addTrainingCourse(trainingCourseDTO);
		LOGGER.info(environment.getProperty("TrainingCourseAPI.INSERT_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@PutMapping("/TrainingCourse")
	public ResponseEntity<TrainingCourseDTO> updateTrainingCourse(@Valid @RequestBody TrainingCourseDTO trainingCourseDTO) throws WomenEmpException{
		TrainingCourseDTO ret =  trainingCourseService.updateTrainingCourse(trainingCourseDTO);
		LOGGER.info(environment.getProperty("TrainingCourseAPI.UPDATE_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/TrainingCourse/{trainingCourseId}")
	public ResponseEntity<TrainingCourseDTO> viewTrainingCourse(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1")
			Integer trainingCourseId) throws WomenEmpException{
		TrainingCourseDTO ret = trainingCourseService.viewTrainingCourse(trainingCourseId);
		LOGGER.info(environment.getProperty("TrainingCourseAPI.VIEW_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/TrainingCourse")
	public ResponseEntity<List<TrainingCourseDTO>> viewAllTrainingCourses() throws WomenEmpException{
		List<TrainingCourseDTO> ret = trainingCourseService.viewAllTrainingCourse();
		LOGGER.info(environment.getProperty("TrainingCourseAPI.VIEW_ALL_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@DeleteMapping("/TrainingCourse/{trainingCourseId}")
	public ResponseEntity<String> deleteTrainingCourse(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1")
			Integer trainingCourseId) throws WomenEmpException{
		trainingCourseService.deleteTrainingCourse(trainingCourseId);
		String message = environment.getProperty("TrainingCourseAPI.DELETE_SUCCESS");
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("/TrainingCourse/courseName/{CourseName}")
	public ResponseEntity<TrainingCourseDTO> viewByTrainingCourseName(@PathVariable 
			@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainingCourse.name.invalid}")
			String CourseName) throws WomenEmpException{
		TrainingCourseDTO ret = trainingCourseService.viewByTrainingCourseName(CourseName);
		LOGGER.info(environment.getProperty("TrainingCourseAPI.VIEW_BY_COURSENAME_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}