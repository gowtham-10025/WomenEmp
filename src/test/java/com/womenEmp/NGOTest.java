package com.womenEmp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.womenEmp.dto.NGODTO;
import com.womenEmp.entity.NGO;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.NGORepository;
import com.womenEmp.service.NGOService;
import com.womenEmp.service.NGOServiceImpl;

@SpringBootTest
class NGOTest {
	@Mock
	NGORepository ngoRepository;
	
	@InjectMocks
	NGOService ngoService = new NGOServiceImpl();
	
	public static NGO demo() {
		NGO ngo = new NGO();
		ngo.setNgoId(1);
		ngo.setNgoName("Women NGO");
		ngo.setNgoLocation("Delhi");
		ngo.setNgoType("Non Government");
		ngo.setNgoMotive("Social upliftment");
		ngo.setDonation(120.5);
		ngo.setNgoSize(30);
		ngo.setNgoActivities("Social Activities");
//		TrainingCourse trainingCourse = TrainingCourse.dtoToEntity(ngoDto.getTrainingCourse());
//		ngo.setTrainingCourse(trainingCourse);
		return ngo;
	}

	@Test
	void validNGOAddition() throws WomenEmpException{
		NGODTO ngoDto = NGODTO.entityToDTO(NGOTest.demo());
		Mockito.when(ngoRepository.findById(ngoDto.getNgoId())).thenReturn(Optional.empty());
		Assertions.assertEquals(ngoService.addNGO(ngoDto), ngoDto);
	}
	
	@Test
	void invalidNGOAddition() throws WomenEmpException{
		NGO ngo = NGOTest.demo();
		Mockito.when(ngoRepository.findById(ngo.getNgoId())).thenReturn(Optional.of(ngo));
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> ngoService.addNGO(NGODTO.entityToDTO(ngo)));
		Assertions.assertEquals(ex.getMessage(), "Service.NGO_ALREADY_PRESENT");
	}
	
	@Test
	void validNGOUpdate() throws WomenEmpException{
		NGO ngo = NGOTest.demo();
		Mockito.when(ngoRepository.findById(ngo.getNgoId())).thenReturn(Optional.of(ngo));
		Assertions.assertEquals(ngoService.updateNGO(NGODTO.entityToDTO(ngo)), NGODTO.entityToDTO(ngo));
	}
	
	@Test
	void invalidNGOUpdate() throws WomenEmpException{
		NGODTO ngoDto = NGODTO.entityToDTO(NGOTest.demo());
		Mockito.when(ngoRepository.findById(ngoDto.getNgoId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> ngoService.updateNGO(ngoDto));
		Assertions.assertEquals(ex.getMessage(), "Service.NGO_NOT_PRESENT");
	}
	
	@Test
	void validViewNGO() throws WomenEmpException{
		NGO ngo = NGOTest.demo();
		Mockito.when(ngoRepository.findById(ngo.getNgoId())).thenReturn(Optional.of(ngo));
		Assertions.assertEquals(ngoService.viewNGO(ngo.getNgoId()), NGODTO.entityToDTO(ngo));
	}
	
	@Test
	void invalidViewNGO() throws WomenEmpException{
		NGODTO ngoDto = NGODTO.entityToDTO(NGOTest.demo());
		Mockito.when(ngoRepository.findById(ngoDto.getNgoId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> ngoService.viewNGO(ngoDto.getNgoId()));
		Assertions.assertEquals(ex.getMessage(), "Service.NGO_NOT_PRESENT");
	}

	@Test
	void validViewAllNGO() throws WomenEmpException{
		NGO ngo = NGOTest.demo();
		List<NGO> list = new ArrayList<>();
		list.add(ngo);
		Mockito.when(ngoRepository.findAll()).thenReturn(list);
		List<NGODTO> listDTO = new ArrayList<>();
		list.forEach(p -> {
			listDTO.add(NGODTO.entityToDTO(p));
		});
		Assertions.assertEquals(ngoService.viewAllNGO(), listDTO);
	}
	
	@Test
	void invalidViewAllNGO() throws WomenEmpException{
		Mockito.when(ngoRepository.findAll()).thenReturn(new ArrayList<NGO>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> ngoService.viewAllNGO());
		Assertions.assertEquals(ex.getMessage(), "Service.NGO_NOT_PRESENT");
	}
	
	@Test
	void validDeleteNGO() throws WomenEmpException{
		NGO ngo = NGOTest.demo();
		Mockito.when(ngoRepository.findById(ngo.getNgoId())).thenReturn(Optional.of(ngo));
		Assertions.assertDoesNotThrow(() -> ngoService.deleteNGO(ngo.getNgoId()));
	}
	
	@Test
	void invalidDeleteNGO() throws WomenEmpException{
		NGODTO ngoDto = NGODTO.entityToDTO(NGOTest.demo());
		Mockito.when(ngoRepository.findById(ngoDto.getNgoId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> ngoService.deleteNGO(ngoDto.getNgoId()));
		Assertions.assertEquals(ex.getMessage(), "Service.NGO_NOT_PRESENT");
	}
	
	@Test
	void validViewAllNGOsByLocation() throws WomenEmpException{
		NGO ngo = NGOTest.demo();
		List<NGO> list = new ArrayList<>();
		list.add(ngo);
		Mockito.when(ngoRepository.findByNgoLocation(ngo.getNgoLocation())).thenReturn(list);
		List<NGODTO> listDTO = new ArrayList<>();
		list.forEach(p->{
			listDTO.add(NGODTO.entityToDTO(p));
		});
		Assertions.assertEquals(ngoService.viewNGOByLocation(ngo.getNgoLocation()), listDTO);
	}
	
	@Test
	void invalidViewAllNGOsByLocation() throws WomenEmpException{
		NGODTO ngoDto = NGODTO.entityToDTO(NGOTest.demo());
		Mockito.when(ngoRepository.findByNgoLocation(ngoDto.getNgoLocation())).thenReturn(new ArrayList<>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> ngoService.viewNGOByLocation(ngoDto.getNgoLocation()));
		Assertions.assertEquals(ex.getMessage(), "Service.NGO_NOT_PRESENT");
	}
	
	@Test
	void validViewNGOByMotive() throws WomenEmpException{
		NGO ngo = NGOTest.demo();
		List<NGO> list = new ArrayList<>();
		list.add(ngo);
		Mockito.when(ngoRepository.findByNgoMotive(ngo.getNgoMotive())).thenReturn(list);
		List<NGODTO> listDTO = new ArrayList<>();
		list.forEach(p->{
			listDTO.add(NGODTO.entityToDTO(p));
		});
		Assertions.assertEquals(ngoService.viewNGOByMotive(ngo.getNgoMotive()), listDTO);
	}
	
	@Test
	void invalidViewNGOByMotive() throws WomenEmpException{
		NGODTO ngoDto = NGODTO.entityToDTO(NGOTest.demo());
		Mockito.when(ngoRepository.findByNgoMotive(ngoDto.getNgoMotive())).thenReturn(new ArrayList<>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> ngoService.viewNGOByMotive(ngoDto.getNgoMotive()));
		Assertions.assertEquals(ex.getMessage(), "Service.NGO_NOT_PRESENT");
	}
}
