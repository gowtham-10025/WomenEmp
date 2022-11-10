package com.womenEmp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.womenEmp.entity.UserLogin;

public interface UserRepository extends JpaRepository<UserLogin, Integer> {
	public UserLogin findByUserName(String userName);
}
