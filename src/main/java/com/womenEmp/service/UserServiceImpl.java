package com.womenEmp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.womenEmp.dto.UserDTO;
import com.womenEmp.entity.UserLogin;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	@Autowired
	UserRepository userDao;

	@Override
	public UserDTO registerUser(UserDTO userDto) throws WomenEmpException {
		Optional<UserLogin> fromRepo = userDao.findById(userDto.getUserId());
		if (fromRepo.isPresent())
			throw new WomenEmpException("Service.USER_ALREADY_PRESENT");

		UserLogin user = UserLogin.dtoToEntity(userDto);
		userDao.save(user);
		return userDto;
	}

	@Override
	public UserDTO login(String userName, String password) throws WomenEmpException {
		UserLogin user = userDao.findByUserName(userName);
		if(user == null) throw new WomenEmpException("Service.USER_NOT_PRESENT");
		if(user.getPassword().equals(password)) {
			return UserDTO.entityToDTO(user);
		}
		throw new WomenEmpException("Service.INCORRECT_PASSWORD");
	}

	@Override
	public UserDTO forgotPassword(UserDTO userDto) throws WomenEmpException {
		Optional<UserLogin> fromRepo = userDao.findById(userDto.getUserId());
		UserLogin user = fromRepo.orElseThrow(()-> new WomenEmpException("Service.USER_NOT_PRESENT"));
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		return UserDTO.entityToDTO(user);
	}

}
