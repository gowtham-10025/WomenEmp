package com.womenEmp.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.womenEmp.dto.SchemeDTO;
import com.womenEmp.entity.Scheme;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.SchemeRepository;
@Service
@Transactional
public class SchemeServiceImpl implements SchemeService{

	/*
	 * Autowired the SchemeDao with service Implementation
	 * autowiring on properties, setters, and constructors
	 * */
	@Autowired
	SchemeRepository schemeDao;
  
  /*
	 * @desc -> Function to add the Scheme in data base
	 * @param -> schemeDTO
	 * @return -> schemeDTO
	 * @Exception -> WomenEmpException
	 * */
	@Override
	public SchemeDTO addScheme(SchemeDTO schemeDTO) throws WomenEmpException {
		Optional<Scheme> schemesFromRepo = schemeDao.findById(schemeDTO.getSchemeId());
		if(schemesFromRepo.isPresent())
			throw new WomenEmpException("Service.SCHEME_ALREADY_PRESENT");
		
		Scheme schemes = Scheme.dtoToEntity(schemeDTO);
		schemeDao.save(schemes);
		return schemeDTO;
	}

	/*
	 * @desc -> Function to update the Scheme in data base
	 * @param -> schemeDTO
	 * @return -> schemeDTO
	 * @Exception -> WomenEmpException
	 * */

	@Override
	public SchemeDTO updateScheme(SchemeDTO schemeDTO) throws WomenEmpException {
		Optional<Scheme> schemesFromRepo = schemeDao.findById(schemeDTO.getSchemeId());
		Scheme schemes = schemesFromRepo.orElseThrow(() -> new WomenEmpException("Service.SCHEME_NOT_PRESENT"));
		
		
		schemes.setLaunchDate(schemeDTO.getLaunchDate());
		schemes.setSchemeId(schemeDTO.getSchemeId());
		schemes.setSchemeName(schemeDTO.getSchemeName());
		schemes.setSchemeEligibility(schemeDTO.getSchemeEligibility());
		schemes.setSchemeObjective(schemeDTO.getSchemeObjective());
		
		return schemeDTO;
	}


	/*
	 * @desc -> Function to view the Scheme based on schemeId
	 * @param -> schemeId
	 * @return -> schemeDTO
	 * @Exception -> WomenEmpException
	 * */
	@Override
	public SchemeDTO viewScheme(int schemeId) throws WomenEmpException {
		// TODO Auto-generated method stub
		Optional<Scheme> schemesFromRepo = schemeDao.findById(schemeId);
		Scheme schemes = schemesFromRepo.orElseThrow(() -> new WomenEmpException("Service.SCHEME_NOT_PRESENT"));
		
		SchemeDTO schemeDTO = SchemeDTO.entityToDTO(schemes);
		return schemeDTO;
	}


	/*
	 * @desc -> Function to view all the Schemes 
	 * @param -> 
	 * @return -> List<schemeDTO>
	 * @Exception -> WomenEmpException
	 * */
	@Override
	public List<SchemeDTO> viewAllScheme() throws WomenEmpException {
		// TODO Auto-generated method stub
		List<Scheme> fromRepo  = schemeDao.findAll();
		if(fromRepo.isEmpty())
			throw new WomenEmpException("Service.SCHEME_NOT_PRESENT");
		List<SchemeDTO> schemeList = new ArrayList<>();
		fromRepo.forEach(entry -> {
			schemeList.add(SchemeDTO.entityToDTO(entry));
		});
		
		
		return schemeList;
	}

	/*
	 * @desc -> Function to delete the Scheme in data base
	 * @param -> schemeId
	 * @return -> void 
	 * @Exception -> WomenEmpException
	 * */
	@Override
	public void deleteScheme(int schemeId) throws WomenEmpException {
		// TODO Auto-generated method stub
		Optional<Scheme> schemesFromRepo = schemeDao.findById(schemeId);
		Scheme schemes = schemesFromRepo.orElseThrow(() -> new WomenEmpException("Service.SCHEME_NOT_PRESENT"));
		schemeDao.delete(schemes);
	}


	/*
	 * @desc -> Function to view the Scheme by Scheme Type
	 * @param -> schemeType
	 * @return -> List<schemeDTO>
	 * @Exception -> WomenEmpException
	 * */
	@Override
	public List<SchemeDTO> viewSchemesByType(String schemeType) throws WomenEmpException {
		
		List<Scheme> fromRepo  = schemeDao.findBySchemeType(schemeType);
		if(fromRepo.isEmpty())
			throw new WomenEmpException("Service.SCHEME_NOT_PRESENT");
		List<SchemeDTO> schemeList = new ArrayList<>();
		fromRepo.forEach(entry -> {
			schemeList.add(SchemeDTO.entityToDTO(entry));
		});
		
		return schemeList;	
	}
	

	/*
	 * @desc -> Function to view Schemes based on Launch date
	 * @param -> LocalDate
	 * @return -> List<schemeDTO>
	 * @Exception -> WomenEmpException
	 * */

	@Override
	public List<SchemeDTO> viewSchemesByLaunchDate(LocalDate date) throws WomenEmpException {
		// TODO Auto-generated method stub
		List<Scheme> fromRepo  = schemeDao.findByLaunchDate(date);
		if(fromRepo.isEmpty())
			throw new WomenEmpException("Service.SCHEME_NOT_PRESENT");
		List<SchemeDTO> schemeList = new ArrayList<>();
		fromRepo.forEach(entry -> {
			schemeList.add(SchemeDTO.entityToDTO(entry));
		});
		
		return schemeList;
	}
	
}