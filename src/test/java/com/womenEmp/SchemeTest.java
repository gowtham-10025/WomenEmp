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

import com.womenEmp.dto.SchemeDTO;
import com.womenEmp.entity.Scheme;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.SchemeRepository;
import com.womenEmp.service.SchemeService;
import com.womenEmp.service.SchemeServiceImpl;

@SpringBootTest
class SchemeTest {
	@Mock
	SchemeRepository schemeDao;

	@InjectMocks
	SchemeService schemeService = new SchemeServiceImpl();

	public static Scheme demo() {
		Scheme schemes = new Scheme();
		schemes.setLaunchDate(LocalDate.of(2007, 01, 8));
		schemes.setSchemeId(1);
		schemes.setSchemeName("Naari Shakti");
		schemes.setSchemeEligibility("Above 18");
		schemes.setSchemeObjective("Adult Education");
		
		return schemes;
	}

	@Test
	void validSchemeAddition()throws WomenEmpException{
			SchemeDTO schemesDTO = SchemeDTO.entityToDTO(SchemeTest.demo());
			Mockito.when(schemeDao.findById(schemesDTO.getSchemeId())).thenReturn(Optional.empty());
			Assertions.assertEquals(schemeService.addScheme(schemesDTO), schemesDTO);
		}
	
	@Test
	void invalidTraineeAddition() throws WomenEmpException{
		Scheme schemes =  SchemeTest.demo();
		Mockito.when(schemeDao.findById(schemes.getSchemeId())).thenReturn(Optional.of(schemes));
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> schemeService.addScheme(SchemeDTO.entityToDTO(schemes)));
		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_ALREADY_PRESENT");
	}
		
	@Test
	void validSchemeUpdate() throws WomenEmpException{
		Scheme schemes =  SchemeTest.demo();
		Mockito.when(schemeDao.findById(schemes.getSchemeId())).thenReturn(Optional.of(schemes));
		Assertions.assertEquals(schemeService.updateScheme(SchemeDTO.entityToDTO(schemes)), SchemeDTO.entityToDTO(schemes));	
	}
		
	@Test
	void invalidSchemeUpdate()throws WomenEmpException{
		SchemeDTO schemesDTO = SchemeDTO.entityToDTO(SchemeTest.demo());
		Mockito.when(schemeDao.findById(schemesDTO.getSchemeId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> schemeService.updateScheme(schemesDTO));
		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_NOT_PRESENT");
	
	}
	
	@Test
	void validViewScheme() throws WomenEmpException{
		Scheme schemes =  SchemeTest.demo();
		Mockito.when(schemeDao.findById(schemes.getSchemeId())).thenReturn(Optional.of(schemes));
		Assertions.assertEquals(schemeService.viewScheme(schemes.getSchemeId()), SchemeDTO.entityToDTO(schemes));	
	}
	
	@Test
	void invalidViewScheme()throws WomenEmpException{
		SchemeDTO schemesDTO = SchemeDTO.entityToDTO(SchemeTest.demo());
		Mockito.when(schemeDao.findById(schemesDTO.getSchemeId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> schemeService.viewScheme(schemesDTO.getSchemeId()));
		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_NOT_PRESENT");
	
	}
	@Test
	void validViewAllScheme() throws WomenEmpException{
		Scheme schemes =  SchemeTest.demo();
		List<Scheme> list = new ArrayList<>();
		list.add(schemes);
		Mockito.when(schemeDao.findAll()).thenReturn(list);
		List<SchemeDTO> listDTO = new ArrayList<>();
		list.forEach(entry ->{
			listDTO.add(SchemeDTO.entityToDTO(entry));
		});
		Assertions.assertEquals(schemeService.viewAllScheme(), listDTO);	
	}
	
	@Test
	void invalidViewAllScheme()throws WomenEmpException{
		Mockito.when(schemeDao.findAll()).thenReturn(new ArrayList<Scheme>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> schemeService.viewAllScheme());
		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_NOT_PRESENT");
	}
	@Test
	void validDeleteScheme() throws WomenEmpException{
		Scheme schemes =  SchemeTest.demo();
		Mockito.when(schemeDao.findById(schemes.getSchemeId())).thenReturn(Optional.of(schemes));
		Assertions.assertDoesNotThrow(() -> schemeService.deleteScheme(schemes.getSchemeId()));
		}
	@Test
	void invalidDeleteScheme()throws WomenEmpException{
		SchemeDTO schemesDTO = SchemeDTO.entityToDTO(SchemeTest.demo());
		Mockito.when(schemeDao.findById(schemesDTO.getSchemeId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> schemeService.deleteScheme(schemesDTO.getSchemeId()));
		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_NOT_PRESENT");
	}
	@Test
	void validViewAllSchemeByType() throws WomenEmpException{
		Scheme schemes =  SchemeTest.demo();
		List<Scheme> list = new ArrayList<>();
		list.add(schemes);
		Mockito.when(schemeDao.findBySchemeType(schemes.getSchemeType())).thenReturn(list);
		List<SchemeDTO> listDTO = new ArrayList<>();
		list.forEach(entry ->{
			listDTO.add(SchemeDTO.entityToDTO(entry));
		});
		Assertions.assertEquals(schemeService.viewSchemesByType(schemes.getSchemeType()), listDTO);	
	}
	@Test
	void invalidViewAllSchemeByType() throws WomenEmpException{
		
		SchemeDTO schemesDTO = SchemeDTO.entityToDTO(SchemeTest.demo());
		Mockito.when(schemeDao.findBySchemeType(schemesDTO.getSchemeType())).thenReturn(new ArrayList<>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> schemeService.viewSchemesByType(schemesDTO.getSchemeType()));
		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_NOT_PRESENT");
	}
	@Test
	void validviewSchemesByLaunchDate() throws WomenEmpException{
		Scheme schemes =  SchemeTest.demo();
		List<Scheme> list = new ArrayList<>();
		list.add(schemes);
		Mockito.when(schemeDao.findByLaunchDate(schemes.getLaunchDate())).thenReturn(list);
		List<SchemeDTO> listDTO = new ArrayList<>();
		list.forEach(entry ->{
			listDTO.add(SchemeDTO.entityToDTO(entry));
		});
		Assertions.assertEquals(schemeService.viewSchemesByLaunchDate(schemes.getLaunchDate()), listDTO);	
	}
	@Test
	void invalidViewAllSchemeByLaunchDate() throws WomenEmpException{
		
		SchemeDTO schemesDTO = SchemeDTO.entityToDTO(SchemeTest.demo());
		Mockito.when(schemeDao.findByLaunchDate(schemesDTO.getLaunchDate())).thenReturn(new ArrayList<>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> schemeService.viewSchemesByLaunchDate(schemesDTO.getLaunchDate()));
		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_NOT_PRESENT");
	}
	
	

}