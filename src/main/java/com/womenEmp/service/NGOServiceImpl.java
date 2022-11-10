package com.womenEmp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.womenEmp.dto.NGODTO;
import com.womenEmp.dto.TrainingCourseDTO;
import com.womenEmp.entity.NGO;
import com.womenEmp.entity.TrainingCourse;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.NGORepository;

@Service
@Transactional
public class NGOServiceImpl implements NGOService{
	
	@Autowired
	NGORepository NGORepository;

//	if ngo not already present then add ngo to database
	@Override
	public NGODTO addNGO(NGODTO ngoDto) throws WomenEmpException {
		Optional<NGO> NGOFromRepo = NGORepository.findById(ngoDto.getNgoId());
		if (NGOFromRepo.isPresent())
			throw new WomenEmpException("Service.NGO_ALREADY_PRESENT");

		NGO ngo = NGO.dtoToEntity(ngoDto);
		NGORepository.save(ngo);
		return ngoDto;
	}

//	if ngoDto found in repository then update else throw exception
	@Override
	public NGODTO updateNGO(NGODTO ngoDto) throws WomenEmpException {
		Optional<NGO> NGOFromRepo = NGORepository.findById(ngoDto.getNgoId());
		NGO ngo = NGOFromRepo.orElseThrow(() -> new WomenEmpException("Service.NGO_NOT_PRESENT"));

		ngo.setNgoId(ngoDto.getNgoId());
		ngo.setNgoName(ngoDto.getNgoName());
		ngo.setNgoLocation(ngoDto.getNgoLocation());
		ngo.setNgoType(ngoDto.getNgoType());
		ngo.setNgoMotive(ngoDto.getNgoMotive());
		ngo.setDonation(ngoDto.getDonation());
		ngo.setNgoSize(ngoDto.getNgoSize());
		ngo.setNgoActivities(ngoDto.getNgoActivities());
		TrainingCourseDTO trainingCourse = ngoDto.getTrainingCourse();
		if(trainingCourse == null) {
			ngo.setTrainingCourse(null);
		}else {
			ngo.setTrainingCourse(TrainingCourse.dtotoEntity(trainingCourse));
		}

		return ngoDto;
	}

//	if ngoId found then return NGODTO else throw exception
	@Override
	public NGODTO viewNGO(int ngoId) throws WomenEmpException {
		Optional<NGO> NGOFromRepo = NGORepository.findById(ngoId);
		NGO ngo = NGOFromRepo.orElseThrow(() -> new WomenEmpException("Service.NGO_NOT_PRESENT"));
		NGODTO ngoDto = NGODTO.entityToDTO(ngo);
		return ngoDto;
	}

//	find all the the ngos in database if list empty then throw exception
	@Override
	public List<NGODTO> viewAllNGO() throws WomenEmpException {
		List<NGO> fromRepo = NGORepository.findAll();
		if(fromRepo.isEmpty()) throw new WomenEmpException("Service.NGO_NOT_PRESENT");
		List<NGODTO> ngos = new ArrayList<>();
		fromRepo.forEach(p -> {
			ngos.add(NGODTO.entityToDTO(p));
		});
		return ngos;
	}

//	if ngoId present in database then delete ngo else throw exception
	@Override
	public void deleteNGO(int ngoId) throws WomenEmpException {
		Optional<NGO> ngoFromRepo = NGORepository.findById(ngoId);
		NGO ngo = ngoFromRepo.orElseThrow(() -> new WomenEmpException("Service.NGO_NOT_PRESENT"));
		ngo.setTrainingCourse(null);
		NGORepository.delete(ngo);
		
	}

//	if ngo motive found in database then return a list of all matched ngos
	@Override
	public List<NGODTO> viewNGOByMotive(String motive) throws WomenEmpException {
		List<NGO> fromRepo = NGORepository.findByNgoMotive(motive);
		if(fromRepo.isEmpty()) throw new WomenEmpException("Service.NGO_NOT_PRESENT");
		List<NGODTO> ngos = new ArrayList<>();
		fromRepo.forEach(p -> {
			ngos.add(NGODTO.entityToDTO(p));
		});
		return ngos;
	}

//	find ngo by location if list is empty throw exception
	@Override
	public List<NGODTO> viewNGOByLocation(String location) throws WomenEmpException {
		List<NGO> fromRepo = NGORepository.findByNgoLocation(location);
		if(fromRepo.isEmpty()) throw new WomenEmpException("Service.NGO_NOT_PRESENT");
		List<NGODTO> ngos = new ArrayList<>();
		fromRepo.forEach(p -> {
			ngos.add(NGODTO.entityToDTO(p));
		});
		return ngos;
	}
	
}
