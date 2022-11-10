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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.womenEmp.dto.FeedbackDTO;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.service.FeedbackService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class FeedbackAPI {
	public static final Log LOGGER = LogFactory.getLog(FeedbackAPI.class);
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/Feedback")
	public ResponseEntity<FeedbackDTO> addFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) throws WomenEmpException{
		FeedbackDTO ret =  feedbackService.addFeedBack(feedbackDTO);
		LOGGER.info(environment.getProperty("FeedbackAPI.INSERT_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@PutMapping("/Feedback")
	public ResponseEntity<FeedbackDTO> updateFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) throws WomenEmpException{
		FeedbackDTO ret =  feedbackService.updateFeedBack(feedbackDTO);
		LOGGER.info(environment.getProperty("FeedbackAPI.UPDATE_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/Feedback/{feedbackId}")
	public ResponseEntity<FeedbackDTO> viewFeedback(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1")
			Integer feedbackId) throws WomenEmpException{
		FeedbackDTO ret = feedbackService.viewFeedBack(feedbackId);
		LOGGER.info(environment.getProperty("FeedbackAPI.VIEW_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/Feedback")
	public ResponseEntity<List<FeedbackDTO>> viewAllFeedback() throws WomenEmpException{
		List<FeedbackDTO> ret = feedbackService.viewAllFeedBack();
		LOGGER.info(environment.getProperty("FeedbackAPI.VIEW_ALL_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/Feedback/Scheme/{schemeName}")
	public ResponseEntity<List<FeedbackDTO>> viewFeedBackBySchemeName(@PathVariable 
			@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{scheme.name.invalid}")
			String schemeName) throws WomenEmpException{
		List<FeedbackDTO> ret = feedbackService.viewFeedBackBySchemeName(schemeName);
		LOGGER.info(environment.getProperty("FeedbackAPI.VIEW_BY_SCHEME_NAME_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/Feedback/TrainingCourse/{courseName}")
	public ResponseEntity<List<FeedbackDTO>> viewFeedBackByTrainingCourseName(@PathVariable 
			@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainingCourse.name.invalid}")
			String courseName) throws WomenEmpException{
		List<FeedbackDTO> ret = feedbackService.viewFeedBackByTrainingCourseName(courseName);
		LOGGER.info(environment.getProperty("FeedbackAPI.VIEW_BY_TRAINING_COURSE_NAME_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}