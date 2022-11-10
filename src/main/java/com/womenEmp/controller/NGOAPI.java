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

import com.womenEmp.dto.NGODTO;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.service.NGOService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class NGOAPI {
	public static final Log LOGGER = LogFactory.getLog(TraineeAPI.class);
	
	@Autowired
	NGOService ngoService;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/NGO")
	public ResponseEntity<NGODTO> addNGO(@Valid @RequestBody NGODTO ngoDto) throws WomenEmpException{
		NGODTO ret =  ngoService.addNGO(ngoDto);
		LOGGER.info(environment.getProperty("NGOAPI.INSERT_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@PutMapping("/NGO")
	public ResponseEntity<NGODTO> updateNGO(@Valid @RequestBody NGODTO ngoDTO) throws WomenEmpException{
		NGODTO ret =  ngoService.updateNGO(ngoDTO);
		LOGGER.info(environment.getProperty("NGOAPI.UPDATE_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/NGO/{ngoId}")
	public ResponseEntity<NGODTO> viewNGO(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1")
			Integer ngoId) throws WomenEmpException{
		NGODTO ret = ngoService.viewNGO(ngoId);
		LOGGER.info(environment.getProperty("NGOAPI.VIEW_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/NGO")
	public ResponseEntity<List<NGODTO>> viewAllNGO() throws WomenEmpException{
		List<NGODTO> ret = ngoService.viewAllNGO();
		LOGGER.info(environment.getProperty("NGOAPI.VIEW_ALL_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@DeleteMapping("/NGO/{ngoId}")
	public ResponseEntity<String> deleteTrainee(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1")
			Integer ngoId) throws WomenEmpException{
		ngoService.deleteNGO(ngoId);
		String message = environment.getProperty("NGOAPI.DELETE_SUCCESS");
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("/NGO/motive/{motive}")
	public ResponseEntity<List<NGODTO>> viewAllTraineesByMotive(@PathVariable 
			@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{ngo.motive.invalid}")
			String motive) throws WomenEmpException{
		List<NGODTO> ret = ngoService.viewNGOByMotive(motive);
		LOGGER.info(environment.getProperty("NGOAPI.VIEW_BY_MOTIVE_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@GetMapping("/NGO/location/{location}")
	public ResponseEntity<List<NGODTO>> viewNGOByLocation(@PathVariable 
			@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{ngo.location.invalid}")
			String location) throws WomenEmpException{
		List<NGODTO> ret = ngoService.viewNGOByLocation(location);
		LOGGER.info(environment.getProperty("NGOAPI.VIEW_BY_LOCATION_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
}
