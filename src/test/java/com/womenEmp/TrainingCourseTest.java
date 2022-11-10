package com.womenEmp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.womenEmp.dto.TrainingCourseDTO;
import com.womenEmp.entity.TrainingCourse;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.TrainingCourseRepository;
import com.womenEmp.service.TrainingCourseService;
import com.womenEmp.service.TrainingCourseServiceImpl;

@SpringBootTest
class TrainingCourseTest {
	@Mock
	TrainingCourseRepository trainingCourseRepository;
	
	@InjectMocks
	TrainingCourseService trainingCourseService = new TrainingCourseServiceImpl();
	
	public static TrainingCourse demo() {
		TrainingCourse trainingcourse = new TrainingCourse();
		trainingcourse.setTrainingCourseId(1);
		trainingcourse.setCourseName("Entrepreneurship in Non-profits");
		trainingcourse.setCourseDuration("18 months");
		trainingcourse.setStartingDate(LocalDate.of(2022, 10, 20));
		trainingcourse.setEndingDate(LocalDate.of(2023, 04, 20));
		trainingcourse.setCourseCompletionStatus("Not Started");
		return trainingcourse;
	}

	@Test
	void validTrainingCourseAddition() throws WomenEmpException{
		TrainingCourseDTO trainingcourseDTO = TrainingCourseDTO.entityToDTO(TrainingCourseTest.demo());
		Mockito.when(trainingCourseRepository.findById(trainingcourseDTO.getTrainingCourseId())).thenReturn(Optional.empty());
		Assertions.assertEquals(trainingCourseService.addTrainingCourse(trainingcourseDTO), trainingcourseDTO);
	}
	
	@Test
	void invalidTrainingCourseAddition() throws WomenEmpException{
		TrainingCourse trainingcourse = TrainingCourseTest.demo();
		Mockito.when(trainingCourseRepository.findById(trainingcourse.getTrainingCourseId())).thenReturn(Optional.of(trainingcourse));
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> trainingCourseService.addTrainingCourse(TrainingCourseDTO.entityToDTO(trainingcourse)));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAININGCOURSE_ALREADY_PRESENT");
	}
	
	@Test
	void validTrainingCourseUpdate() throws WomenEmpException{
		TrainingCourse trainingcourse = TrainingCourseTest.demo();
		Mockito.when(trainingCourseRepository.findById(trainingcourse.getTrainingCourseId())).thenReturn(Optional.of(trainingcourse));
		Assertions.assertEquals(trainingCourseService.updateTrainingCourse(TrainingCourseDTO.entityToDTO(trainingcourse)), TrainingCourseDTO.entityToDTO(trainingcourse));
	}
	
	@Test
	void invalidTrainingCourseUpdate() throws WomenEmpException{
		TrainingCourseDTO trainingcourseDTO = TrainingCourseDTO.entityToDTO(TrainingCourseTest.demo());
		Mockito.when(trainingCourseRepository.findById(trainingcourseDTO.getTrainingCourseId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> trainingCourseService.updateTrainingCourse(trainingcourseDTO));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAININGCOURSE_NOT_PRESENT");
	}
	
	@Test
	void validViewTrainingCourse() throws WomenEmpException{
		TrainingCourse trainingcourse = TrainingCourseTest.demo();
		Mockito.when(trainingCourseRepository.findById(trainingcourse.getTrainingCourseId())).thenReturn(Optional.of(trainingcourse));
		Assertions.assertEquals(trainingCourseService.viewTrainingCourse(trainingcourse.getTrainingCourseId()), TrainingCourseDTO.entityToDTO(trainingcourse));
	}
	
	@Test
	void invalidViewTrainingCourse() throws WomenEmpException{
		TrainingCourseDTO trainingCourseDTO = TrainingCourseDTO.entityToDTO(TrainingCourseTest.demo());
		Mockito.when(trainingCourseRepository.findById(trainingCourseDTO.getTrainingCourseId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> trainingCourseService.viewTrainingCourse(trainingCourseDTO.getTrainingCourseId()));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAININGCOURSE_NOT_PRESENT");
	}

	@Test
	void validViewAllTrainingCourses() throws WomenEmpException{
		TrainingCourse trainingcourse = TrainingCourseTest.demo();
		List<TrainingCourse> list = new ArrayList<>();
		list.add(trainingcourse);
		Mockito.when(trainingCourseRepository.findAll()).thenReturn(list);
		List<TrainingCourseDTO> listDTO = new ArrayList<>();
		list.forEach(p -> {
			listDTO.add(TrainingCourseDTO.entityToDTO(p));
		});
		Assertions.assertEquals(trainingCourseService.viewAllTrainingCourse(), listDTO);
	}
	
	@Test
	void invalidViewAllTrainingCourse() throws WomenEmpException{
		Mockito.when(trainingCourseRepository.findAll()).thenReturn(new ArrayList<TrainingCourse>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> trainingCourseService.viewAllTrainingCourse());
		Assertions.assertEquals(ex.getMessage(), "Service.TRAININGCOURSE_NOT_PRESENT");
	}
	
	@Test
	void validDeleteTrainingCourse() throws WomenEmpException{
		TrainingCourse trainingcourse = TrainingCourseTest.demo();
		Mockito.when(trainingCourseRepository.findById(trainingcourse.getTrainingCourseId())).thenReturn(Optional.of(trainingcourse));
		Assertions.assertDoesNotThrow(() -> trainingCourseService.deleteTrainingCourse(trainingcourse.getTrainingCourseId()));
	}
	
	@Test
	void invalidDeleteTrainingCourse() throws WomenEmpException{
		TrainingCourseDTO traineeDTO = TrainingCourseDTO.entityToDTO(TrainingCourseTest.demo());
		Mockito.when(trainingCourseRepository.findById(traineeDTO.getTrainingCourseId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> trainingCourseService.deleteTrainingCourse(traineeDTO.getTrainingCourseId()));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAININGCOURSE_NOT_PRESENT");
	}
	
	@Test
	void validViewTrainingByCourseName() throws WomenEmpException{
		TrainingCourse trainingcourse = TrainingCourseTest.demo();
		Mockito.when(trainingCourseRepository.findByCourseName(trainingcourse.getCourseName())).thenReturn(trainingcourse);
		Assertions.assertEquals(trainingCourseService.viewByTrainingCourseName(trainingcourse.getCourseName()), TrainingCourseDTO.entityToDTO(trainingcourse));
	}
	
	@Test
	void invalidViewTrainingByCourseName() throws WomenEmpException{
		TrainingCourseDTO trainingCourseDTO = TrainingCourseDTO.entityToDTO(TrainingCourseTest.demo());
		Mockito.when(trainingCourseRepository.findByCourseName(trainingCourseDTO.getCourseName())).thenReturn(null);
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> trainingCourseService.viewByTrainingCourseName(trainingCourseDTO.getCourseName()));
		Assertions.assertEquals(ex.getMessage(), "Service.INVALID_COURSE_NAME");
	}
}