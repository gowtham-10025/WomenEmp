package com.womenEmp.dto;

import java.time.LocalDate;
import java.util.Objects;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import com.womenEmp.entity.Feedback;
import com.womenEmp.entity.Trainee;
import com.womenEmp.entity.TrainingCourse;

public class TraineeDTO {
	/**
	 * Automatically generated trainee Id
	 */
	private Integer traineeId;

	/**
	 * Trainee username should contain alphabets only
	 */
	@NotNull(message = "{trainee.username.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainee.username.invalid}")
	private String userName;

	/**
	 * Trainee password can contain alphabets or digits
	 */
	@NotNull(message = "{trainee.password.absent}")
	@Pattern(regexp = "[a-zA-Z0-9]+", message = "{trainee.password.invalid}")
	private String password;

	/**
	 * Trainee firstname should contain alphabets only
	 */
	@NotNull(message = "{trainee.firstname.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainee.firstname.invalid}")
	private String firstName;
	/**
	 * Trainee lastName should contain alphabets only
	 */
	@NotNull(message = "{trainee.lastname.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainee.lastname.invalid}")
	private String lastName;
	/**
	 * Trainee location should contain alphabets only
	 */
	@NotNull(message = "{trainee.location.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{trainee.location.invalid}")
	private String location;
	/**
	 * Contact number should be a 10 digit number
	 */
	@Min(value = 1000000000L, message = "{trainee.contact.invalid}")
	@Max(value = 9999999999L, message = "{trainee.contact.invalid}")
	private long contact;
	/**
	 * Email should be valid
	 */
	@Email(message = "{trainee.email.invalid}")
	private String email;
	private String familyInfo;
	/**
	 * aadhar number should be 12 digit number
	 */
	@Min(value = 100000000000L, message = "{trainee.aadharNo.invalid}")
	@Max(value = 999999999999L, message = "{trainee.aadharNo.invalid}")
	private long aadharNo;
	/**
	 * date of birth should be in past
	 */
	@Past(message = "{trainee.dob.invalid}")
	private LocalDate dob;
	private TrainingCourseDTO trainingCourseDTO;
	private FeedbackDTO feedbackDTO;

	public static TraineeDTO entityToDTO(Trainee trainee) {
		TraineeDTO traineeDTO = new TraineeDTO();
		traineeDTO.setTraineeId(trainee.getTraineeId());
		traineeDTO.setUserName(trainee.getUserName());
		traineeDTO.setPassword(trainee.getPassword());
		traineeDTO.setFirstName(trainee.getFirstName());
		traineeDTO.setLastName(trainee.getLastName());
		traineeDTO.setLocation(trainee.getLocation());
		traineeDTO.setContact(trainee.getContact());
		traineeDTO.setEmail(trainee.getEmail());
		traineeDTO.setFamilyInfo(trainee.getFamilyInfo());
		traineeDTO.setAadharNo(trainee.getAadharNo());
		traineeDTO.setDob(trainee.getDob());
		TrainingCourse trainingCourse = trainee.getTrainingCourse();
		if (trainingCourse == null) {
			traineeDTO.setTrainingCourse(null);
		} else {
			traineeDTO.setTrainingCourse(TrainingCourseDTO.entityToDTO(trainingCourse));
		}

		Feedback feedback = trainee.getFeedback();
		if (feedback == null) {
			traineeDTO.setFeedback(null);
		} else {
			traineeDTO.setFeedback(FeedbackDTO.entityToDTO(feedback));
		}
		return traineeDTO;

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

	public TrainingCourseDTO getTrainingCourse() {
		return trainingCourseDTO;
	}

	public void setTrainingCourse(TrainingCourseDTO trainingCourseDTO) {
		this.trainingCourseDTO = trainingCourseDTO;
	}

	public FeedbackDTO getFeedback() {
		return feedbackDTO;
	}

	public void setFeedback(FeedbackDTO feedbackDTO) {
		this.feedbackDTO = feedbackDTO;
	}

	@Override
	public String toString() {
		return "TraineeDTO [traineeId=" + traineeId + ", userName=" + userName + ", password=" + password
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", location=" + location + ", contact="
				+ contact + ", email=" + email + ", familyInfo=" + familyInfo + ", aadharNo=" + aadharNo + ", dob="
				+ dob + ", trainingCourseDTO=" + trainingCourseDTO + ", feedbackDTO=" + feedbackDTO + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(aadharNo, contact, dob, email, familyInfo, firstName, lastName, location, password,
				traineeId, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TraineeDTO other = (TraineeDTO) obj;
		return aadharNo == other.aadharNo && contact == other.contact && Objects.equals(dob, other.dob)
				&& Objects.equals(email, other.email) && Objects.equals(familyInfo, other.familyInfo)
//				&& Objects.equals(feedbackDTO, other.feedbackDTO) 
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(location, other.location) && Objects.equals(password, other.password)
				&& Objects.equals(traineeId, other.traineeId)
//				&& Objects.equals(trainingCourseDTO, other.trainingCourseDTO)
				&& Objects.equals(userName, other.userName);
	}

}
