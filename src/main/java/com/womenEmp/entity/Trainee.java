package com.womenEmp.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.womenEmp.dto.FeedbackDTO;
import com.womenEmp.dto.TraineeDTO;
import com.womenEmp.dto.TrainingCourseDTO;

@Entity
public class Trainee {
	/**
	 * @GeneratedValue is used to automatically generate primary key value
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer traineeId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String location;
	private long contact;
	private String email;
	private String familyInfo;
	private long aadharNo;
	private LocalDate dob;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "training_course_id", unique = true)
	private TrainingCourse trainingCourse;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "feedback_id", unique = true)
	private Feedback feedback;
	
	public static Trainee dtoToEntity(TraineeDTO traineeDTO) {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(traineeDTO.getTraineeId());
		trainee.setUserName(traineeDTO.getUserName());
		trainee.setPassword(traineeDTO.getPassword());
		trainee.setFirstName(traineeDTO.getFirstName());
		trainee.setLastName(traineeDTO.getLastName());
		trainee.setLocation(traineeDTO.getLocation());
		trainee.setContact(traineeDTO.getContact());
		trainee.setEmail(traineeDTO.getEmail());
		trainee.setFamilyInfo(traineeDTO.getFamilyInfo());
		trainee.setAadharNo(traineeDTO.getAadharNo());
		trainee.setDob(traineeDTO.getDob());
		TrainingCourseDTO trainingCoursedto = traineeDTO.getTrainingCourse();
		if(trainingCoursedto == null) {
			trainee.setTrainingCourse(null);
		}else {
			trainee.setTrainingCourse(TrainingCourse.dtotoEntity(trainingCoursedto));
		}
		
		FeedbackDTO feedbackdto = traineeDTO.getFeedback();
		if(feedbackdto == null) {
			trainee.setFeedback(null);
		}else {
			trainee.setFeedback(Feedback.dtoToEntity(feedbackdto));
		}
		return trainee;
	}
	
	public Integer getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFamilyInfo() {
		return familyInfo;
	}
	public void setFamilyInfo(String familyInfo) {
		this.familyInfo = familyInfo;
	}
	public long getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(long aadharNo) {
		this.aadharNo = aadharNo;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public TrainingCourse getTrainingCourse() {
		return trainingCourse;
	}
	public void setTrainingCourse(TrainingCourse trainingCourse) {
		this.trainingCourse = trainingCourse;
	}
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", location=" + location + ", contact=" + contact + ", email="
				+ email + ", familyInfo=" + familyInfo + ", aadharNo=" + aadharNo + ", dob=" + dob + ", trainingCourse="
				+ trainingCourse + ", feedback=" + feedback + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(aadharNo, contact, dob, email, familyInfo, firstName, lastName, location,
				password, traineeId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainee other = (Trainee) obj;
		return aadharNo == other.aadharNo && contact == other.contact && Objects.equals(dob, other.dob)
				&& Objects.equals(email, other.email) && Objects.equals(familyInfo, other.familyInfo)
//				&& Objects.equals(feedback, other.feedback) 
				&& Objects.equals(firstName, other.firstName)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(location, other.location)
//				&& Objects.equals(trainingCourse, other.trainingCourse) 
				&& Objects.equals(userName, other.userName)
				&& Objects.equals(password, other.password) && Objects.equals(traineeId, other.traineeId);
	}
	
}