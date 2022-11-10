package com.womenEmp.controller;

import java.time.LocalDate;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.womenEmp.dto.SchemeDTO;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.service.SchemeService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class SchemeAPI {
	public static final Log LOGGER = LogFactory.getLog(SchemeAPI.class);
	

	@Autowired
	SchemeService schemeService;
	
	@Autowired
	Environment environment;
	
	/*
	 * For POST request to add the schemes
	 * */
	@PostMapping("/Schemes")
	public ResponseEntity<SchemeDTO> addScheme(@Valid @RequestBody SchemeDTO schemesDTO) throws WomenEmpException {
		SchemeDTO ret =  schemeService.addScheme(schemesDTO);
		LOGGER.info(environment.getProperty("SchemeAPI.INSERT_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/*
	 * For POST request to add the schemes
	 * */
	@PutMapping("/Schemes")
	public ResponseEntity<SchemeDTO> updateScheme(@Valid @RequestBody SchemeDTO schemesDTO) throws WomenEmpException {
		SchemeDTO ret =  schemeService.updateScheme(schemesDTO);
		LOGGER.info(environment.getProperty("SchemeAPI.UPDATE_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/*
	 * For GET request to view the schemes based on id
	 * */
	@GetMapping("/Schemes/{schemeId}")
	public ResponseEntity<SchemeDTO> viewScheme(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1")
			Integer schemeId ) throws WomenEmpException {
		SchemeDTO ret =  schemeService.viewScheme(schemeId);
		LOGGER.info(environment.getProperty("SchemeAPI.VIEW_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/*
	 * For GET request to view all the schemes
	 * */
	
	@GetMapping("/Schemes")
	public ResponseEntity<List<SchemeDTO>> viewAllScheme() throws WomenEmpException {
		List<SchemeDTO> ret = schemeService.viewAllScheme();
		LOGGER.info(environment.getProperty("SchemeAPI.VIEW_ALL_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/*
	 * For DELETE  request to delete the schemes based on SchemeID
	 * */
	
	@DeleteMapping("/Schemes/{schemeId}")
	public ResponseEntity<String> deleteScheme(@PathVariable 
			@Min(value = 1, message = "Id should be greater than 1")
			Integer schemeId)throws WomenEmpException {
	
		schemeService.deleteScheme(schemeId);
		String message = environment.getProperty("SchemeAPI.DELETE_SUCCESS");
		LOGGER.info(message);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}

	/*
	 * For GET request to view the schemes based on scheme type
	 * */
	@GetMapping("/Schemes/schemeType/{schemeType}")
	public ResponseEntity<List<SchemeDTO>> viewSchemesByType(@PathVariable 
			@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{scheme.type.invalid}")
			String schemeType) throws WomenEmpException {
		List<SchemeDTO> ret =  schemeService.viewSchemesByType(schemeType);
		LOGGER.info(environment.getProperty("SchemeAPI.VIEW_BY_SCHEME_TYPE"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}

	/*
	 * For GET request to view the schemes based on their launch date
	 * */
	@GetMapping("/Schemes/date/{date}")
	public ResponseEntity<List<SchemeDTO>> viewSchemesByLaunchDate(@PathVariable
			@PastOrPresent(message = "{scheme.launchdate.invalid}")
	@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) throws WomenEmpException {
		List<SchemeDTO> ret =  schemeService.viewSchemesByLaunchDate(date);
		LOGGER.info(environment.getProperty("SchemeAPI.VIEW_BY_SCHEME_DATE"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	
}