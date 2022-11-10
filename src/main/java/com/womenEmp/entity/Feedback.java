package com.womenEmp.entity;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import com.womenEmp.dto.FeedbackDTO;
import com.womenEmp.dto.SchemeDTO;
import com.womenEmp.dto.TrainingCourseDTO;
import com.womenEmp.dto.UserDTO; 

@Entity
@NamedQuery(name = "Feedback.viewFeedBackBySchemeName", query = "SELECT f FROM Feedback f INNER JOIN Scheme s ON f.scheme.schemeId = s.schemeId"
		+ " WHERE s.schemeName = :schemeName ")
@NamedQuery(name = "Feedback.viewFeedBackByTrainingCourseName", query = "SELECT f FROM Feedback f INNER JOIN TrainingCourse t ON f.trainingCourse.trainingCourseId = t.trainingCourseId"
		+ " WHERE t.courseName = :courseName ")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedBackId;
	private int schemeRating;
	private int schemeTrainingRating;
	private int overallRating;
	private String comment;
	private LocalDate date;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", unique = true)
	private UserLogin user;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "training_course_id", unique = true)
	private TrainingCourse trainingCourse;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "scheme_id", unique = true)
	private Scheme scheme;
	
	
	public Feedback() {
	
	}
	public static Feedback dtoToEntity(FeedbackDTO feedbackDTO) {
		Feedback feedback = new Feedback();

		feedback.setFeedBackId(feedbackDTO.getFeedBackId());
		feedback.setSchemeRating(feedbackDTO.getSchemeRating());
		feedback.setSchemeTrainingRating(feedbackDTO.getSchemeTrainingRating());
		feedback.setOverallRating(feedbackDTO.getOverallRating());
		feedback.setComment(feedbackDTO.getComment());
		feedback.setDate(feedbackDTO.getDate());
		
		UserDTO user = feedbackDTO.getUser();
		if(user == null) {
			feedback.setUser(null);
		}else {
			feedback.setUser(UserLogin.dtoToEntity(user));
		}
		TrainingCourseDTO trainingCourse = feedbackDTO.getTrainingCourse();
		if(trainingCourse == null) {
			feedback.setTrainingCourse(null);
		}else {
			feedback.setTrainingCourse(TrainingCourse.dtotoEntity(trainingCourse));
		}
		SchemeDTO scheme = feedbackDTO.getScheme();
		if(scheme == null) {
			feedback.setScheme(null);
		}else {
			feedback.setScheme(Scheme.dtoToEntity(scheme));
		}

	
		return feedback;

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
	public UserLogin getUser() {
		return user;
	}
	public void setUser(UserLogin user) {
		this.user = user;
	}
	public TrainingCourse getTrainingCourse() {
		return trainingCourse;
	}
	public void setTrainingCourse(TrainingCourse trainingCourse) {
		this.trainingCourse = trainingCourse;
	}
	public Scheme getScheme() {
		return scheme;
	}
	public void setScheme(Scheme scheme) {
		this.scheme = scheme;
	}
	@Override
	public String toString() {
		return "Feedback [feedBackId=" + feedBackId + ", schemeRating=" + schemeRating + ", schemeTrainingRating="
				+ schemeTrainingRating + ", overallRating=" + overallRating + ", comment=" + comment + ", date=" + date
				+ ", user=" + user+  ", trainingCourse=" + trainingCourse + ", scheme=" + scheme + "]";
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
		Feedback other = (Feedback) obj;
		return Objects.equals(comment, other.comment) && Objects.equals(date, other.date)
				&& Objects.equals(feedBackId, other.feedBackId) && overallRating == other.overallRating
//				&& Objects.equals(scheme, other.scheme) 
				&& schemeRating == other.schemeRating
//				&& Objects.equals(trainingCourse, other.trainingCourse) && Objects.equals(user, other.user)
				&& schemeTrainingRating == other.schemeTrainingRating;
	}
	
	
	
}