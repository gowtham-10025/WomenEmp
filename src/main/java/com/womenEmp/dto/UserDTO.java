package com.womenEmp.dto;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.womenEmp.entity.UserLogin;

public class UserDTO {
	Integer userId;
	@NotNull(message = "{user.username.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{user.username.invalid}")
	String userName;
	@NotNull(message = "{user.password.absent}")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "{user.password.invalid}")
	String password;
	
	public static UserDTO entityToDTO(UserLogin user) {
		UserDTO userDto = new UserDTO();
		userDto.setUserId(user.getUserId());
		userDto.setPassword(user.getPassword());
		userDto.setUserName(user.getUserName());
		return userDto;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserLogin [userId=" + userId + ", userName=" + userName + ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(password, userId, userName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return Objects.equals(password, other.password) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName);
	}
	
}
