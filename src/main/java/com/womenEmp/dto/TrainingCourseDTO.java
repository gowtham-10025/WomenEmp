package com.womenEmp.dto;

import java.time.LocalDate;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.womenEmp.entity.TrainingCourse;

public class TrainingCourseDTO {
	int trainingCourseId;
	@NotNull(message = "{trainingCourse.name.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainingCourse.name.invalid}")
	String courseName;
	@NotNull(message = "{trainingCourse.duration.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainingCourse.duration.invalid}")
	String courseDuration;
	LocalDate startingDate;
	LocalDate endingDate;
	@NotNull(message = "{trainingCourse.status.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainingCourse.status.invalid}")
	String courseCompletionStatus;
	
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
	
	
	public static TrainingCourseDTO entityToDTO(TrainingCourse trainingCourse) {
		TrainingCourseDTO trainingCourseDTO = new TrainingCourseDTO();
		trainingCourseDTO.setTrainingCourseId(trainingCourse.getTrainingCourseId());
		trainingCourseDTO.setCourseName(trainingCourse.getCourseName());
		trainingCourseDTO.setCourseDuration(trainingCourse.getCourseDuration());
		trainingCourseDTO.setStartingDate(trainingCourse.getStartingDate());
		trainingCourseDTO.setEndingDate(trainingCourse.getEndingDate());
		trainingCourseDTO.setCourseCompletionStatus(trainingCourse.getCourseCompletionStatus());
		return trainingCourseDTO;
	}
	public String toString() {
		return "TrainingCourse [trainingCourseId=" + trainingCourseId + ", courseName=" + courseName + ", CourseDuration=" + courseDuration + ", StartingDate="
				+ startingDate + ", endingDate=" + endingDate + ", courseCompletionStatus=" + courseCompletionStatus + "]";
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
		TrainingCourseDTO other = (TrainingCourseDTO) obj;
		return Objects.equals(courseCompletionStatus, other.courseCompletionStatus)
				&& Objects.equals(courseDuration, other.courseDuration) && Objects.equals(courseName, other.courseName)
				&& Objects.equals(endingDate, other.endingDate) && Objects.equals(startingDate, other.startingDate)
				&& trainingCourseId == other.trainingCourseId;
	}

	

}