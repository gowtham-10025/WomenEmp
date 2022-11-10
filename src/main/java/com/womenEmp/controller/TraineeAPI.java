package com.womenEmp.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
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

import com.womenEmp.dto.TraineeDTO;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.service.TraineeService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class TraineeAPI {
	public static final Log LOGGER = LogFactory.getLog(TraineeAPI.class);

	@Autowired
	TraineeService traineeService;

	@Autowired
	Environment environment;

	/**
	 * Add trainee to the database
	 * 
	 * @param traineeDTO
	 * @return ResponseEntity<TraineeDTO>
	 * @throws WomenEmpException
	 */

	@PostMapping("/Trainee")
	public ResponseEntity<TraineeDTO> addTrainee(@Valid @RequestBody TraineeDTO traineeDTO) throws WomenEmpException {
		TraineeDTO ret = traineeService.addTrainee(traineeDTO);
		LOGGER.info(environment.getProperty("TraineeAPI.INSERT_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}

	/**
	 * Update trainee details
	 * 
	 * @param traineeDTO
	 * @return ResponseEntity<TraineeDTO>
	 * @throws WomenEmpException
	 */

	@PutMapping("/Trainee")
	public ResponseEntity<TraineeDTO> updateTrainee(@Valid @RequestBody TraineeDTO traineeDTO)
			throws WomenEmpException {
		TraineeDTO ret = traineeService.updateTrainee(traineeDTO);
		LOGGER.info(environment.getProperty("TraineeAPI.UPDATE_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Get trainee by Trainee Id
	 * 
	 * @param traineeId
	 * @return ResponseEntity<TraineeDTO>
	 * @throws WomenEmpException
	 */

	@GetMapping("/Trainee/{traineeId}")
	public ResponseEntity<TraineeDTO> viewTrainee(
			@PathVariable @Min(value = 1, message = "traineeId should be greater than 0") Integer traineeId)
			throws WomenEmpException {
		TraineeDTO ret = traineeService.viewTrainee(traineeId);
		LOGGER.info(environment.getProperty("TraineeAPI.VIEW_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Get all trainees
	 * 
	 * @return ResponseEntity<List<TraineeDTO>>
	 * @throws WomenEmpException
	 */

	@GetMapping("/Trainee")
	public ResponseEntity<List<TraineeDTO>> viewAllTrainee() throws WomenEmpException {
		List<TraineeDTO> ret = traineeService.viewAllTrainee();
		LOGGER.info(environment.getProperty("TraineeAPI.VIEW_ALL_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Delete Trainee by ID
	 * 
	 * @param traineeId
	 * @return ResponseEntity<String>
	 * @throws WomenEmpException
	 */

	@DeleteMapping("/Trainee/{traineeId}")
	public ResponseEntity<String> deleteTrainee(
			@PathVariable @Min(value = 1, message = "traineeId should be greater than 0") Integer traineeId)
			throws WomenEmpException {
		traineeService.deleteTrainee(traineeId);
		String message = environment.getProperty("TraineeAPI.DELETE_SUCCESS");
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/**
	 * Get trainee by location
	 * 
	 * @param location
	 * @return ResponseEntity<List<TraineeDTO>>
	 * @throws WomenEmpException
	 */

	@GetMapping("/Trainee/location/{location}")
	public ResponseEntity<List<TraineeDTO>> viewAllTraineesByLocation(
			@PathVariable @Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainee.location.invalid}") String location)
			throws WomenEmpException {
		List<TraineeDTO> ret = traineeService.viewAllTraineeByLocation(location);
		LOGGER.info(environment.getProperty("TraineeAPI.VIEW_BY_LOCATION_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/**
	 * Get trainee by aadhar No, Aadhar number should be 12 digit number
	 * 
	 * @param aadharNo
	 * @return ResponseEntity<TraineeDTO>
	 * @throws WomenEmpException
	 */

	@GetMapping("/Trainee/aadhar/{aadharNo}")
	public ResponseEntity<TraineeDTO> viewTraineeByAadhar(
			@PathVariable @Min(value = 100000000000L, message = "{trainee.aadharNo.invalid}") @Max(value = 999999999999L, message = "{trainee.aadharNo.invalid}") long aadharNo)
			throws WomenEmpException {
		TraineeDTO ret = traineeService.viewTraineeByAadhar(aadharNo);
		LOGGER.info(environment.getProperty("TraineeAPI.VIEW_BY_AADHAR_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
