package com.womenEmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.womenEmp.entity.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Integer> {
	Trainee findByAadharNo(long aadharNo);
	List<Trainee> findByLocation(String location);
}