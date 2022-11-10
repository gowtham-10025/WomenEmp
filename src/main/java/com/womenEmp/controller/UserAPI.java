package com.womenEmp.controller;

import javax.validation.Valid;
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

import com.womenEmp.dto.UserDTO;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
@Validated
public class UserAPI {
	public static final Log LOGGER = LogFactory.getLog(UserAPI.class);
	
	@Autowired
	UserService userService;
	
	@Autowired
	Environment environment;
	
	@PostMapping("/UserLogin")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) throws WomenEmpException{
		UserDTO ret =  userService.registerUser(userDTO);
		LOGGER.info(environment.getProperty("UserAPI.REGISTER_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
	
	@GetMapping("/UserLogin/{username}/{password}")
	public ResponseEntity<UserDTO> login(@PathVariable 
			@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{user.username.invalid}")
			String username, 
			@PathVariable 
			@Pattern(regexp = "[a-zA-Z0-9]+", message = "{user.password.invalid}")
			String password) throws WomenEmpException{
		UserDTO ret =  userService.login(username, password);
		LOGGER.info(environment.getProperty("UserAPI.LOGIN_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.OK);
	}
	
	@PutMapping("/UserLogin")
	public ResponseEntity<UserDTO> forgotPassword(@Valid @RequestBody UserDTO userDTO) throws WomenEmpException{
		UserDTO ret =  userService.forgotPassword(userDTO);
		LOGGER.info(environment.getProperty("UserAPI.PASSWORD_UPDATE_SUCCESS"));
		return new ResponseEntity<>(ret, HttpStatus.CREATED);
	}
}
