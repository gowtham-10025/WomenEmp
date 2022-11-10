package com.womenEmp.service;

import com.womenEmp.dto.UserDTO;
import com.womenEmp.exception.WomenEmpException;

public interface UserService {
	public UserDTO registerUser(UserDTO userDto) throws WomenEmpException;
	public UserDTO login(String userName, String password) throws WomenEmpException;
	public UserDTO forgotPassword(UserDTO userDto) throws WomenEmpException;
}
