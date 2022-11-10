package com.womenEmp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.womenEmp.entity.NGO;

public interface NGORepository extends JpaRepository<NGO, Integer> {
	public List<NGO> findByNgoMotive(String motive);
	public List<NGO> findByNgoLocation(String location);
}
