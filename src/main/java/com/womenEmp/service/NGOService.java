package com.womenEmp.service;

import java.util.List;

import com.womenEmp.dto.NGODTO;
import com.womenEmp.exception.WomenEmpException;

public interface NGOService {
	public NGODTO addNGO(NGODTO ngoDto) throws WomenEmpException;
	public NGODTO updateNGO(NGODTO ngoDto) throws WomenEmpException;
	public NGODTO viewNGO(int ngoId) throws WomenEmpException;
	public List<NGODTO> viewAllNGO() throws WomenEmpException;
	public void deleteNGO(int ngoId) throws WomenEmpException;
	public List<NGODTO> viewNGOByMotive(String motive) throws WomenEmpException;
	public List<NGODTO> viewNGOByLocation(String location) throws WomenEmpException;
}
