package com.womenEmp.service;

import java.time.LocalDate;
import java.util.List;

import com.womenEmp.dto.SchemeDTO;
import com.womenEmp.exception.WomenEmpException;

public interface SchemeService {
	public SchemeDTO addScheme(SchemeDTO schemeDTO) throws WomenEmpException;
	public SchemeDTO updateScheme(SchemeDTO schemeDTO)throws WomenEmpException;
	public SchemeDTO viewScheme(int schemeId)throws WomenEmpException;
	public List<SchemeDTO> viewAllScheme()throws WomenEmpException;
	public void deleteScheme(int schemeId)throws WomenEmpException;
	public List<SchemeDTO> viewSchemesByType(String schemeType)throws WomenEmpException;
	public List<SchemeDTO> viewSchemesByLaunchDate(LocalDate date)throws WomenEmpException;
	
	
}