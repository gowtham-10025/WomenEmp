package com.womenEmp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.womenEmp.dto.TraineeDTO;
import com.womenEmp.entity.Trainee;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.TraineeRepository;

@Transactional
@Service
public class TraineeServiceImpl implements TraineeService {
	@Autowired
	TraineeRepository traineeRepository;

	@Override
	public TraineeDTO addTrainee(TraineeDTO traineeDTO) throws WomenEmpException {
		Optional<Trainee> traineeFromRepo = traineeRepository.findById(traineeDTO.getTraineeId());
		Trainee fromRepoByAadhar = traineeRepository.findByAadharNo(traineeDTO.getAadharNo());
		if (traineeFromRepo.isPresent())
			throw new WomenEmpException("Service.TRAINEE_ALREADY_PRESENT");
		if(fromRepoByAadhar != null)
			throw new WomenEmpException("Service.AADHAR_NUMBER_ALREADY_PRESENT");
		traineeRepository.save(Trainee.dtoToEntity(traineeDTO));
		return traineeDTO;
	}

	@Override
	public TraineeDTO updateTrainee(TraineeDTO traineeDTO) throws WomenEmpException {
		Optional<Trainee> traineeFromRepo = traineeRepository.findById(traineeDTO.getTraineeId());
		Trainee trainee = traineeFromRepo.orElseThrow(() -> new WomenEmpException("Service.TRAINEE_NOT_PRESENT"));

		trainee.setUserName(traineeDTO.getUserName());
		trainee.setPassword(traineeDTO.getPassword());
		trainee.setFirstName(traineeDTO.getFirstName());
		trainee.setLastName(traineeDTO.getLastName());
		trainee.setContact(traineeDTO.getContact());
		trainee.setEmail(traineeDTO.getEmail());
		trainee.setFamilyInfo(traineeDTO.getFamilyInfo());
		trainee.setAadharNo(traineeDTO.getAadharNo());
		trainee.setDob(traineeDTO.getDob());

		return traineeDTO;
	}

	@Override
	public TraineeDTO viewTrainee(Integer traineeId) throws WomenEmpException {
		Optional<Trainee> traineeFromRepo = traineeRepository.findById(traineeId);
		Trainee trainee = traineeFromRepo.orElseThrow(() -> new WomenEmpException("Service.TRAINEE_NOT_PRESENT"));
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(trainee);
		return traineeDTO;
	}

	@Override
	public List<TraineeDTO> viewAllTrainee() throws WomenEmpException {
		List<Trainee> fromRepo = traineeRepository.findAll();
		if (fromRepo.isEmpty())
			throw new WomenEmpException("Service.TRAINEE_NOT_PRESENT");
		List<TraineeDTO> trainees = new ArrayList<>();
		fromRepo.forEach(p -> {
			trainees.add(TraineeDTO.entityToDTO(p));
		});
		return trainees;
	}

	@Override
	public void deleteTrainee(Integer traineeId) throws WomenEmpException {
		Optional<Trainee> traineeFromRepo = traineeRepository.findById(traineeId);
		Trainee trainee = traineeFromRepo.orElseThrow(() -> new WomenEmpException("Service.TRAINEE_NOT_PRESENT"));
		trainee.setFeedback(null);
		trainee.setTrainingCourse(null);
		traineeRepository.delete(trainee);
	}

	@Override
	public List<TraineeDTO> viewAllTraineeByLocation(String location) throws WomenEmpException {
		List<Trainee> fromRepo = traineeRepository.findByLocation(location);
		if (fromRepo.isEmpty())
			throw new WomenEmpException("Service.TRAINEE_NOT_PRESENT");
		List<TraineeDTO> trainees = new ArrayList<>();
		fromRepo.forEach(p -> {
			trainees.add(TraineeDTO.entityToDTO(p));
		});
		return trainees;
	}

	@Override
	public TraineeDTO viewTraineeByAadhar(long aadharNo) throws WomenEmpException {
		Trainee fromRepo = traineeRepository.findByAadharNo(aadharNo);
		if (fromRepo == null)
			throw new WomenEmpException("Service.INVALID_AADHAR");
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(fromRepo);
		return traineeDTO;
	}

}
