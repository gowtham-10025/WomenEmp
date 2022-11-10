package com.womenEmp.service;
import java.util.List;

import com.womenEmp.dto.FeedbackDTO;
import com.womenEmp.exception.WomenEmpException;

public interface FeedbackService {

	public FeedbackDTO addFeedBack(FeedbackDTO feedbackDTO) throws WomenEmpException;
	public FeedbackDTO updateFeedBack(FeedbackDTO feedbackDTO) throws WomenEmpException;
	public FeedbackDTO viewFeedBack(Integer feedbackId) throws WomenEmpException;
	public List<FeedbackDTO> viewAllFeedBack() throws WomenEmpException;
	public List<FeedbackDTO> viewFeedBackBySchemeName(String schemeName) throws WomenEmpException;
	public List<FeedbackDTO> viewFeedBackByTrainingCourseName(String courseName) throws WomenEmpException;
	
}