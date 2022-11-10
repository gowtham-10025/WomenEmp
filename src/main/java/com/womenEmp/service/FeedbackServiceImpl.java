package com.womenEmp.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.womenEmp.dto.FeedbackDTO;
import com.womenEmp.entity.Feedback;
import com.womenEmp.exception.WomenEmpException;
import com.womenEmp.repository.FeedbackRepository;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{
		@Autowired
		FeedbackRepository feedbackRepository;

		@Override
		public FeedbackDTO addFeedBack(FeedbackDTO feedbackDTO) throws WomenEmpException {
			Optional<Feedback> feedbackfromRepo = feedbackRepository.findById(feedbackDTO.getFeedBackId());
			if (feedbackfromRepo.isPresent())
				throw new WomenEmpException("Service.FEEDBACK_ALREADY_PRESENT");

			Feedback feedback = Feedback.dtoToEntity(feedbackDTO);
			feedbackRepository.save(feedback);
			return feedbackDTO;
		}

		@Override
		public FeedbackDTO updateFeedBack(FeedbackDTO feedbackDTO) throws WomenEmpException {
			Optional<Feedback> feedbackfromRepo = feedbackRepository.findById(feedbackDTO.getFeedBackId());
			Feedback feedback = feedbackfromRepo.orElseThrow(() -> new WomenEmpException("Service.FEEDBACK_NOT_PRESENT"));

			feedback.setSchemeRating(feedbackDTO.getSchemeRating());
			feedback.setSchemeTrainingRating(feedbackDTO.getSchemeTrainingRating());
			feedback.setOverallRating(feedbackDTO.getOverallRating());
			feedback.setComment(feedbackDTO.getComment());
			feedback.setDate(feedbackDTO.getDate());

			return feedbackDTO;
			
		}

		@Override
		public FeedbackDTO viewFeedBack(Integer feedbackId) throws WomenEmpException {
			Optional<Feedback> feedbackfromRepo = feedbackRepository.findById(feedbackId);
			Feedback feedback = feedbackfromRepo.orElseThrow(() -> new WomenEmpException("Service.FEEDBACK_NOT_PRESENT"));
			FeedbackDTO feedbackDTO = FeedbackDTO.entityToDTO(feedback);
			return feedbackDTO;
		}

		@Override
		public List<FeedbackDTO> viewAllFeedBack() throws WomenEmpException {
			List<Feedback> fromRepo = feedbackRepository.findAll();
			if(fromRepo.isEmpty()) throw new WomenEmpException("Service.FEEDBACK_NOT_PRESENT");
			List<FeedbackDTO> feedbacks = new ArrayList<>();
			fromRepo.forEach(p -> {
				feedbacks.add(FeedbackDTO.entityToDTO(p));
			});
			return feedbacks;
		}
		@Override
		public List<FeedbackDTO> viewFeedBackBySchemeName(String schemeName) throws WomenEmpException {
			List<Feedback> fromRepo = feedbackRepository.viewFeedBackBySchemeName(schemeName);
			if(fromRepo.isEmpty()) throw new WomenEmpException("Service.FEEDBACK_NOT_PRESENT");
			List<FeedbackDTO> feedbacks = new ArrayList<>();
			fromRepo.forEach(p -> {
				feedbacks.add(FeedbackDTO.entityToDTO(p));
			});
			return feedbacks;
		}

		@Override
		public List<FeedbackDTO> viewFeedBackByTrainingCourseName(String courseName) throws WomenEmpException {
			List<Feedback> fromRepo = feedbackRepository.viewFeedBackByTrainingCourseName(courseName);
			if(fromRepo.isEmpty()) throw new WomenEmpException("Service.FEEDBACK_NOT_PRESENT");
			List<FeedbackDTO> feedbacks = new ArrayList<>();
			fromRepo.forEach(p -> {
				feedbacks.add(FeedbackDTO.entityToDTO(p));
			});
			return feedbacks;
		}

	}