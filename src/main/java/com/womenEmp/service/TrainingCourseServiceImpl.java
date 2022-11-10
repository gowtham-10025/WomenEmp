package com.womenEmp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.womenEmp.dto.TrainingCourseDTO;
import com.womenEmp.entity.TrainingCourse;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.TrainingCourseRepository;

@Transactional
@Service
public class TrainingCourseServiceImpl implements TrainingCourseService {
	@Autowired
	TrainingCourseRepository trainingCourseRepository;

	@Override
	public TrainingCourseDTO addTrainingCourse(TrainingCourseDTO trainingCourseDTO) throws WomenEmpException {
		Optional<TrainingCourse> trainingCourseFromRepo = trainingCourseRepository.findById(trainingCourseDTO.getTrainingCourseId());
		if (trainingCourseFromRepo.isPresent())
			throw new WomenEmpException("Service.TRAININGCOURSE_ALREADY_PRESENT");

		TrainingCourse trainingCourse = TrainingCourse.dtotoEntity(trainingCourseDTO);
		trainingCourseRepository.save(trainingCourse);
		return trainingCourseDTO;
	}

	@Override
	public TrainingCourseDTO updateTrainingCourse(TrainingCourseDTO trainingCourseDTO) throws WomenEmpException {
		Optional<TrainingCourse> trainingCourseFromRepo = trainingCourseRepository.findById(trainingCourseDTO.getTrainingCourseId());
		TrainingCourse trainingCourse = trainingCourseFromRepo.orElseThrow(() -> new WomenEmpException("Service.TRAININGCOURSE_NOT_PRESENT"));

		trainingCourse.setTrainingCourseId(trainingCourseDTO.getTrainingCourseId());
		trainingCourse.setCourseName(trainingCourseDTO.getCourseName());
		trainingCourse.setCourseDuration(trainingCourseDTO.getCourseDuration());
		trainingCourse.setStartingDate(trainingCourseDTO.getStartingDate());
		trainingCourse.setEndingDate(trainingCourseDTO.getEndingDate());
		trainingCourse.setCourseCompletionStatus(trainingCourseDTO.getCourseCompletionStatus());
		return trainingCourseDTO;
	}

	@Override
	public TrainingCourseDTO viewTrainingCourse(Integer trainingCourseId) throws WomenEmpException {
		Optional<TrainingCourse> trainingCourseFromRepo = trainingCourseRepository.findById(trainingCourseId);
		TrainingCourse trainingCourse = trainingCourseFromRepo.orElseThrow(() -> new WomenEmpException("Service.TRAININGCOURSE_NOT_PRESENT"));
		TrainingCourseDTO trainingCourseDTO = TrainingCourseDTO.entityToDTO(trainingCourse);
		return trainingCourseDTO;
	}

	@Override
	public List<TrainingCourseDTO> viewAllTrainingCourse() throws WomenEmpException {
		List<TrainingCourse> fromRepo = trainingCourseRepository.findAll();
		if(fromRepo.isEmpty()) throw new WomenEmpException("Service.TRAININGCOURSE_NOT_PRESENT");
		List<TrainingCourseDTO> trainingCourses = new ArrayList<>();
		fromRepo.forEach(p -> {
			trainingCourses.add(TrainingCourseDTO.entityToDTO(p));
		});
		return trainingCourses;
	}

	@Override
	public void deleteTrainingCourse(Integer trainingCourseId) throws WomenEmpException {
		Optional<TrainingCourse> trainingCourseFromRepo = trainingCourseRepository.findById(trainingCourseId);
		TrainingCourse trainingCourse = trainingCourseFromRepo.orElseThrow(() -> new WomenEmpException("Service.TRAININGCOURSE_NOT_PRESENT"));
		trainingCourseRepository.delete(trainingCourse);
	}

	@Override
	public TrainingCourseDTO viewByTrainingCourseName(String courseName) throws WomenEmpException {
		TrainingCourse fromRepo = trainingCourseRepository.findByCourseName(courseName);
		if(fromRepo == null) throw new WomenEmpException("Service.INVALID_COURSE_NAME");
		TrainingCourseDTO trainingCourseDTO = TrainingCourseDTO.entityToDTO(fromRepo);
		return trainingCourseDTO;
	}

}