package com.womenEmp;


import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.womenEmp.dto.UserDTO;
import com.womenEmp.entity.UserLogin;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.UserRepository;
import com.womenEmp.service.UserService;
import com.womenEmp.service.UserServiceImpl;

@SpringBootTest
class UserTest {
	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserService userService = new UserServiceImpl();
	
	public static UserLogin demo() {
		UserLogin user = new UserLogin();
		user.setUserId(1);
		user.setUserName("gaurav");
		user.setPassword("gaurav123");
		return user;
	}

	@Test
	void validRegisterUser() throws WomenEmpException{
		UserDTO userDTO = UserDTO.entityToDTO(UserTest.demo());
		Mockito.when(userRepository.findById(userDTO.getUserId())).thenReturn(Optional.empty());
		Assertions.assertEquals(userService.registerUser(userDTO), userDTO);
	}
	
	@Test
	void invalidRegisterUser() throws WomenEmpException{
		UserLogin user = UserTest.demo();
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> userService.registerUser(UserDTO.entityToDTO(user)));
		Assertions.assertEquals(ex.getMessage(), "Service.USER_ALREADY_PRESENT");
	}
	@Test
	void validLogin() throws WomenEmpException{
		UserLogin user = UserTest.demo();
		Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
		Assertions.assertEquals(userService.login(user.getUserName(), user.getPassword()), UserDTO.entityToDTO(user));
	}
	
	@Test
	void invalidLogin() throws WomenEmpException{
		UserLogin user = UserTest.demo();
		Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(null);
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> userService.login(user.getUserName(), user.getPassword()));
		Assertions.assertEquals(ex.getMessage(), "Service.USER_NOT_PRESENT");
	}
	
	@Test
	void incorrectPassword() throws WomenEmpException{
		UserLogin user = UserTest.demo();
		Mockito.when(userRepository.findByUserName(user.getUserName())).thenReturn(user);
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> userService.login(user.getUserName(), "incorrectPassword"));
		Assertions.assertEquals(ex.getMessage(), "Service.INCORRECT_PASSWORD");
	}
	
	@Test
	void validForgotPassowrd() throws WomenEmpException{
		UserLogin user = UserTest.demo();
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.of(user));
		Assertions.assertEquals(userService.forgotPassword(UserDTO.entityToDTO(user)), UserDTO.entityToDTO(user));
	}
	@Test
	void invalidForgotPassowrd() throws WomenEmpException{
		UserLogin user = UserTest.demo();
		Mockito.when(userRepository.findById(user.getUserId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> userService.forgotPassword(UserDTO.entityToDTO(user)));
		Assertions.assertEquals(ex.getMessage(), "Service.USER_NOT_PRESENT");
	}
}
