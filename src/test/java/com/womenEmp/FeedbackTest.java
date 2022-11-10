package com.womenEmp;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import com.womenEmp.dto.FeedbackDTO;
import com.womenEmp.entity.Feedback;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.FeedbackRepository;
import com.womenEmp.service.FeedbackService;
import com.womenEmp.service.FeedbackServiceImpl;

@SpringBootTest
class FeedbackTest {
	@Mock
	FeedbackRepository feedbackRepository;
	
	@InjectMocks
	FeedbackService feedbackservice = new FeedbackServiceImpl();
	
	public static Feedback demo() {
		Feedback feedback = new Feedback();
		feedback.setFeedBackId(1);
		feedback.setSchemeRating(6);
		feedback.setSchemeTrainingRating(8);
		feedback.setOverallRating(7);
		feedback.setComment("Very good learning experience");
		feedback.setDate(LocalDate.now());
		feedback.setTrainingCourse(null);
		feedback.setScheme(null);
		
		return feedback;
	}

	@Test
	void validFeedbackAddition() throws WomenEmpException{
		FeedbackDTO feedbackdto = FeedbackDTO.entityToDTO(FeedbackTest.demo());
		Mockito.when(feedbackRepository.findById(feedbackdto.getFeedBackId())).thenReturn(Optional.empty());
		Assertions.assertEquals(feedbackservice.addFeedBack(feedbackdto), feedbackdto);
	}
	
	@Test
	void invalidFeedbackAddition() throws WomenEmpException{
		Feedback feedback = FeedbackTest.demo();
		Mockito.when(feedbackRepository.findById(feedback.getFeedBackId())).thenReturn(Optional.of(feedback));
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> feedbackservice.addFeedBack(FeedbackDTO.entityToDTO(feedback)));
		Assertions.assertEquals(ex.getMessage(), "Service.FEEDBACK_ALREADY_PRESENT");
	}
	
	@Test
	void validFeedbackUpdate() throws WomenEmpException{
		Feedback feedback = FeedbackTest.demo();
		Mockito.when(feedbackRepository.findById(feedback.getFeedBackId())).thenReturn(Optional.of(feedback));
		Assertions.assertEquals(feedbackservice.updateFeedBack(FeedbackDTO.entityToDTO(feedback)), FeedbackDTO.entityToDTO(feedback));
	}
	
	@Test
	void invalidFeedbackUpdate() throws WomenEmpException{
		FeedbackDTO feedbackdto = FeedbackDTO.entityToDTO(FeedbackTest.demo());
		Mockito.when(feedbackRepository.findById(feedbackdto.getFeedBackId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, () -> feedbackservice.updateFeedBack(feedbackdto));
		Assertions.assertEquals(ex.getMessage(), "Service.FEEDBACK_NOT_PRESENT");
	}
	void validViewFeedback() throws WomenEmpException{
		Feedback feedback = FeedbackTest.demo();
		Mockito.when(feedbackRepository.findById(feedback.getFeedBackId())).thenReturn(Optional.of(feedback));
		Assertions.assertEquals(feedbackservice.viewFeedBack(feedback.getFeedBackId()), FeedbackDTO.entityToDTO(feedback));
	}
	
	@Test
	void invalidViewFeedback() throws WomenEmpException{
		FeedbackDTO feedbackDTO = FeedbackDTO.entityToDTO(FeedbackTest.demo());
		Mockito.when(feedbackRepository.findById(feedbackDTO.getFeedBackId())).thenReturn(Optional.empty());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> feedbackservice.viewFeedBack(feedbackDTO.getFeedBackId()));
		Assertions.assertEquals(ex.getMessage(), "Service.FEEDBACK_NOT_PRESENT");
	}

	@Test
	void validViewAllFeedback() throws WomenEmpException{
		Feedback feedback = FeedbackTest.demo();
		List<Feedback> list = new ArrayList<>();
		list.add(feedback);
		Mockito.when(feedbackRepository.findAll()).thenReturn(list);
		List<FeedbackDTO> listDTO = new ArrayList<>();
		list.forEach(p -> {
			listDTO.add(FeedbackDTO.entityToDTO(p));
		});
		Assertions.assertEquals(feedbackservice.viewAllFeedBack(), listDTO);
	}
	
	@Test
	void invalidViewAllFeedback() throws WomenEmpException{
		Mockito.when(feedbackRepository.findAll()).thenReturn(new ArrayList<Feedback>());
		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> feedbackservice.viewAllFeedBack());
		Assertions.assertEquals(ex.getMessage(), "Service.FEEDBACK_NOT_PRESENT");
	}
//	@Test
//	void ViewFeedBackBySchemeName() throws WomenEmpException{
//		Feedback feedback = FeedbackTest.demo();
//		Mockito.when(feedbackRepository.viewFeedBackBySchemeName(feedback.getScheme().getSchemeName())).thenReturn(new ArrayList<>());
//		Assertions.assertEquals(feedbackservice.viewFeedBackBySchemeName(feedback.getTrainingCourse().getSchemeName()), FeedbackDTO.entityToDTO(feedback));
//	}
//	@Test
//	void invalidViewFeedBackBySchemeName() throws WomenEmpException{
//		FeedbackDTO feedbackDTO = FeedbackDTO.entityToDTO(FeedbackTest.demo());
//		Mockito.when(feedbackRepository.findByLocation(feedbackDTO.getScheme().getSchemeName())).thenReturn(new ArrayList<>());
//		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> feedbackservice.viewFeedBackBySchemeName(feedbackDTO.getScheme().getSchemeName()));
//		Assertions.assertEquals(ex.getMessage(), "Service.SCHEME_NOT_PRESENT");
//	}
//	
//	@Test
//	void viewFeedBackByTrainingCourseName() throws WomenEmpException{
//		Feedback feedback = FeedbackTest.demo();
//		Mockito.when(feedbackRepository.viewFeedBackByTrainingCourseName(feedback.getTrainingCourse().getCourseName())).thenReturn(feedback);
//		Assertions.assertEquals(feedbackservice.viewFeedBackByTrainingCourseName(feedback.getTrainingCourse().getCourseName()), FeedbackDTO.entityToDTO(feedback));
//	}
//	
//	@Test
//	void invalidViewFeedBackByTrainingCourseName() throws WomenEmpException{
//		FeedbackDTO feedbackDTO = FeedbackDTO.entityToDTO(FeedbackTest.demo());
//		Mockito.when(feedbackRepository.findByAadharNo(feedbackDTO.getTrainingCourse().getCourseName())).thenReturn(null);
//		WomenEmpException ex = Assertions.assertThrows(WomenEmpException.class, ()-> feedbackservice.viewFeedBackByTrainingCourseName(feedbackDTO.getTrainingCourse().getCourseName()));
//		Assertions.assertEquals(ex.getMessage(), "Service.TRAINING_COURSE_NOT_PRESENT");
//	}
}