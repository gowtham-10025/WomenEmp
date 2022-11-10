package com.womenEmp.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.womenEmp.dto.TrainingCourseDTO;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class TrainingCourse {
	@Id
	@GeneratedValue
	private int trainingCourseId;
	private String courseName;
	private String courseDuration;
	private LocalDate startingDate;
	private LocalDate endingDate;
	private String courseCompletionStatus;
	
	public static TrainingCourse dtotoEntity(TrainingCourseDTO trainingCourseDTO) {
		TrainingCourse trainingCourse = new TrainingCourse();
		trainingCourse.setTrainingCourseId(trainingCourseDTO.getTrainingCourseId());
		trainingCourse.setCourseName(trainingCourseDTO.getCourseName());
		trainingCourse.setCourseDuration(trainingCourseDTO.getCourseDuration());
		trainingCourse.setStartingDate(trainingCourseDTO.getStartingDate());
		trainingCourse.setEndingDate(trainingCourseDTO.getEndingDate());
		trainingCourse.setCourseCompletionStatus(trainingCourseDTO.getCourseCompletionStatus());
		return trainingCourse;
	}
	
	public int getTrainingCourseId() {
		return trainingCourseId;
	}
	public void setTrainingCourseId(int trainingCourseId) {
		this.trainingCourseId = trainingCourseId;
	}
	
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	
	public String getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(String courseDuration) {
		this.courseDuration = courseDuration;
	}
	
	
	public LocalDate getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}
	
	
	public LocalDate getEndingDate() {
		return endingDate;
	}
	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}
	
	
	public String getCourseCompletionStatus() {
		return courseCompletionStatus;
	}
	public void setCourseCompletionStatus(String courseCompletionStatus) {
		this.courseCompletionStatus = courseCompletionStatus;
	}

	@Override
	public String toString() {
		return "TrainingCourse [trainingCourseId=" + trainingCourseId + ", courseName=" + courseName
				+ ", courseDuration=" + courseDuration + ", startingDate=" + startingDate + ", endingDate=" + endingDate
				+ ", courseCompletionStatus=" + courseCompletionStatus + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseCompletionStatus, courseDuration, courseName, endingDate, startingDate,
				trainingCourseId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TrainingCourse other = (TrainingCourse) obj;
		return Objects.equals(courseCompletionStatus, other.courseCompletionStatus)
				&& Objects.equals(courseDuration, other.courseDuration) && Objects.equals(courseName, other.courseName)
				&& Objects.equals(endingDate, other.endingDate) && Objects.equals(startingDate, other.startingDate)
				&& trainingCourseId == other.trainingCourseId;
	}
	
	
	
	

}