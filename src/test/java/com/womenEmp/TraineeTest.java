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

import com.womenEmp.dto.TraineeDTO;
import com.womenEmp.entity.Trainee;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.TraineeRepository;
import com.womenEmp.service.TraineeService;
import com.womenEmp.service.TraineeServiceImpl;

@SpringBootTest
class TraineeTest {
	@Mock
	TraineeRepository traineeRepository;
	
	@InjectMocks
	TraineeService traineeService = new TraineeServiceImpl();
	
	public static Trainee demo() {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(1);
		trainee.setUserName("gauravTrainee");
		trainee.setPassword("gaurav1234");
		trainee.setFirstName("Gaurav");
		trainee.setLastName("Singh");
		trainee.setContact(1234567890L);
		trainee.setEmail("example@email.com");
		trainee.setFamilyInfo("Family of 4");
		trainee.setAadharNo(123456789012L);
		trainee.setDob(LocalDate.of(2000, 01, 01));
		trainee.setTrainingCourse(null);
		trainee.setFeedback(null);
		return trainee;
	}

	@Test
	void validTraineeAddition() throws WomenEmpException{
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(TraineeTest.demo());
		Mockito.when(traineeRepository.findById(traineeDTO.getTraineeId())).thenReturn(Optional.empty());
		Assertions.assertEquals(traineeService.addTrainee(traineeDTO), traineeDTO);
	}
	
	@Test
	void invalidTraineeAddition() throws WomenEmpException{
		Trainee trainee = TraineeTest.demo();
		Mockito.when(traineeRepository.findById(trainee.getTraineeId())).thenReturn(Optional.of(trainee));
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> traineeService.addTrainee(TraineeDTO.entityToDTO(trainee)));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAINEE_ALREADY_PRESENT");
	}
	
	@Test
	void validTraineeUpdate() throws WomenEmpException{
		Trainee trainee = TraineeTest.demo();
		Mockito.when(traineeRepository.findById(trainee.getTraineeId())).thenReturn(Optional.of(trainee));
		Assertions.assertEquals(traineeService.updateTrainee(TraineeDTO.entityToDTO(trainee)), TraineeDTO.entityToDTO(trainee));
	}
	
	@Test
	void invalidTraineeUpdate() throws WomenEmpException{
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(TraineeTest.demo());
		Mockito.when(traineeRepository.findById(traineeDTO.getTraineeId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> traineeService.updateTrainee(traineeDTO));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAINEE_NOT_PRESENT");
	}
	
	@Test
	void validViewTrainee() throws WomenEmpException{
		Trainee trainee = TraineeTest.demo();
		Mockito.when(traineeRepository.findById(trainee.getTraineeId())).thenReturn(Optional.of(trainee));
		Assertions.assertEquals(traineeService.viewTrainee(trainee.getTraineeId()), TraineeDTO.entityToDTO(trainee));
	}
	
	@Test
	void invalidViewTrainee() throws WomenEmpException{
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(TraineeTest.demo());
		Mockito.when(traineeRepository.findById(traineeDTO.getTraineeId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> traineeService.viewTrainee(traineeDTO.getTraineeId()));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAINEE_NOT_PRESENT");
	}

	@Test
	void validViewAllTrainee() throws WomenEmpException{
		Trainee trainee = TraineeTest.demo();
		List<Trainee> list = new ArrayList<>();
		list.add(trainee);
		Mockito.when(traineeRepository.findAll()).thenReturn(list);
		List<TraineeDTO> listDTO = new ArrayList<>();
		list.forEach(p -> {
			listDTO.add(TraineeDTO.entityToDTO(p));
		});
		Assertions.assertEquals(traineeService.viewAllTrainee(), listDTO);
	}
	
	@Test
	void invalidViewAllTrainee() throws WomenEmpException{
		Mockito.when(traineeRepository.findAll()).thenReturn(new ArrayList<Trainee>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> traineeService.viewAllTrainee());
		Assertions.assertEquals(ex.getMessage(), "Service.TRAINEE_NOT_PRESENT");
	}
	
	@Test
	void validDeleteTrainee() throws WomenEmpException{
		Trainee trainee = TraineeTest.demo();
		Mockito.when(traineeRepository.findById(trainee.getTraineeId())).thenReturn(Optional.of(trainee));
		Assertions.assertDoesNotThrow(() -> traineeService.deleteTrainee(trainee.getTraineeId()));
	}
	
	@Test
	void invalidDeleteTrainee() throws WomenEmpException{
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(TraineeTest.demo());
		Mockito.when(traineeRepository.findById(traineeDTO.getTraineeId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> traineeService.deleteTrainee(traineeDTO.getTraineeId()));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAINEE_NOT_PRESENT");
	}
	
	@Test
	void validViewAllTraineesByLocation() throws WomenEmpException{
		Trainee trainee = TraineeTest.demo();
		List<Trainee> list = new ArrayList<>();
		list.add(trainee);
		Mockito.when(traineeRepository.findByLocation(trainee.getLocation())).thenReturn(list);
		List<TraineeDTO> listDTO = new ArrayList<>();
		list.forEach(p->{
			listDTO.add(TraineeDTO.entityToDTO(p));
		});
		Assertions.assertEquals(traineeService.viewAllTraineeByLocation(trainee.getLocation()), listDTO);
	}
	
	@Test
	void invalidViewAllTraineesByLocation() throws WomenEmpException{
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(TraineeTest.demo());
		Mockito.when(traineeRepository.findByLocation(traineeDTO.getLocation())).thenReturn(new ArrayList<>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> traineeService.viewAllTraineeByLocation(traineeDTO.getLocation()));
		Assertions.assertEquals(ex.getMessage(), "Service.TRAINEE_NOT_PRESENT");
	}
	
	@Test
	void validViewTraineeByAadhar() throws WomenEmpException{
		Trainee trainee = TraineeTest.demo();
		Mockito.when(traineeRepository.findByAadharNo(trainee.getAadharNo())).thenReturn(trainee);
		Assertions.assertEquals(traineeService.viewTraineeByAadhar(trainee.getAadharNo()), TraineeDTO.entityToDTO(trainee));
	}
	
	@Test
	void invalidViewTraineeByAadhar() throws WomenEmpException{
		TraineeDTO traineeDTO = TraineeDTO.entityToDTO(TraineeTest.demo());
		Mockito.when(traineeRepository.findByAadharNo(traineeDTO.getAadharNo())).thenReturn(null);
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> traineeService.viewTraineeByAadhar(traineeDTO.getAadharNo()));
		Assertions.assertEquals(ex.getMessage(), "Service.INVALID_AADHAR");
	}
}
