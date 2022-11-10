package com.womenEmp.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.womenEmp.entity.Feedback;
import com.womenEmp.entity.Scheme;
import com.womenEmp.entity.TrainingCourse;
import com.womenEmp.entity.UserLogin;

public class FeedbackDTO {
	private Integer feedBackId;
	@NotNull(message = "{feedback.rating.absent}")
	@Min(value = 1, message = "{feedback.rating.invalid}")
	@Max(value = 10, message = "{feedback.rating.invalid}")
	private int schemeRating;
	@NotNull(message = "{feedback.trianingrating.absent}")
	@Min(value = 1, message = "{feedback.rating.invalid}")
	@Max(value = 10, message = "{feedback.rating.invalid}")
	private int schemeTrainingRating;
	@NotNull(message = "{feedback.overallrating.absent}")
	@Min(value = 1, message = "{feedback.rating.invalid}")
	@Max(value = 10, message = "{feedback.rating.invalid}")
	private int overallRating;
	private String comment;
	private LocalDate date;
	private UserDTO user;
	private TrainingCourseDTO trainingCourse;
	private SchemeDTO scheme;
	
	public static FeedbackDTO entityToDTO(Feedback feedback) {
		FeedbackDTO feedbackDTO = new FeedbackDTO();

		feedbackDTO.setFeedBackId(feedback.getFeedBackId());
		feedbackDTO.setSchemeRating(feedback.getSchemeRating());
		feedbackDTO.setSchemeTrainingRating(feedback.getSchemeTrainingRating());
		feedbackDTO.setOverallRating(feedback.getOverallRating());
		feedbackDTO.setComment(feedback.getComment());
		feedbackDTO.setDate(feedback.getDate());
		
		UserLogin user = feedback.getUser(); 
		if(user == null) {
			feedbackDTO.setUser(null);
		}else {
			feedbackDTO.setUser(UserDTO.entityToDTO(user));
		}
		TrainingCourse trainingCourse = feedback.getTrainingCourse();
		if(trainingCourse == null) {
			feedbackDTO.setTrainingCourse(null);
		}else {
			feedbackDTO.setTrainingCourse(TrainingCourseDTO.entityToDTO(trainingCourse));
		}
		Scheme scheme = feedback.getScheme();
		if(scheme == null) {
			feedbackDTO.setScheme(null);
		}else {
			feedbackDTO.setScheme(SchemeDTO.entityToDTO(scheme));
		}
		
		return feedbackDTO;

	}
	
	public int getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(int feedBackId) {
		this.feedBackId = feedBackId;
	}
	public int getSchemeRating() {
		return schemeRating;
	}
	public void setSchemeRating(int schemeRating) {
		this.schemeRating = schemeRating;
	}
	public int getSchemeTrainingRating() {
		return schemeTrainingRating;
	}
	public void setSchemeTrainingRating(int schemeTrainingRating) {
		this.schemeTrainingRating = schemeTrainingRating;
	}
	public int getOverallRating() {
		return overallRating;
	}
	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}


	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public TrainingCourseDTO getTrainingCourse() {
		return trainingCourse;
	}
	public void setTrainingCourse(TrainingCourseDTO trainingCourse) {
		this.trainingCourse = trainingCourse;
	}
	public SchemeDTO getScheme() {
		return scheme;
	}
	public void setScheme(SchemeDTO scheme) {
		this.scheme = scheme;
	}

	@Override
	public String toString() {
		return "FeedbackDTO [feedBackId=" + feedBackId + ", schemeRating=" + schemeRating + ", schemeTrainingRating="
				+ schemeTrainingRating + ", overallRating=" + overallRating + ", comment=" + comment + ", date=" + date
				+", user=" + user + ", trainingCourse=" + trainingCourse + ", scheme=" + scheme + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment, date, feedBackId, overallRating, scheme, schemeRating, schemeTrainingRating,
				trainingCourse, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FeedbackDTO other = (FeedbackDTO) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(date, other.date)
				&& Objects.equals(feedBackId, other.feedBackId) && overallRating == other.overallRating
//				&& Objects.equals(scheme, other.scheme) 
				&& schemeRating == other.schemeRating
//				&& Objects.equals(trainingCourse, other.trainingCourse) && Objects.equals(user, other.user)
				&& schemeTrainingRating == other.schemeTrainingRating;
	}

	
	
}