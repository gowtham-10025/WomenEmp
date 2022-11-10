package com.womenEmp.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.womenEmp.dto.UserDTO;

@Entity
public class UserLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer userId;
	String userName;
	String password;
	
	public static UserLogin dtoToEntity(UserDTO userDto) {
		UserLogin user = new UserLogin();
		user.setUserId(userDto.getUserId());
		user.setPassword(userDto.getPassword());
		user.setUserName(userDto.getUserName());
		return user;
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
		UserLogin other = (UserLogin) obj;
		return Objects.equals(password, other.password) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName);
	}
	
	
}
