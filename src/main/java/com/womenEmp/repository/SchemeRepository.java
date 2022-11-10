package com.womenEmp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.womenEmp.entity.Scheme;

public interface SchemeRepository extends JpaRepository<Scheme, Integer>{
	List<Scheme> findBySchemeType(String schemeType);
	List<Scheme> findByLaunchDate(LocalDate date);
	
}