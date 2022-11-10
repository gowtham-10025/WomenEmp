package com.womenEmp.dto;

import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.womenEmp.entity.NGO;
import com.womenEmp.entity.TrainingCourse;


public class NGODTO {
	Integer ngoId;
//	ngo name, ngolocation, ngotype, ngomotive, ngoActivities should contain alpahbets only
//	ngo donation value should be between 1000 and 10000
//	ngo size should be between 1 and 100
	@NotNull(message = "{ngo.ngoname.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{ngo.ngoname.invalid}")
	String ngoName;
	@NotNull(message = "{ngo.location.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{ngo.location.invalid}")
	String ngoLocation;
	@NotNull(message = "{ngo.type.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{ngo.type.invalid}")
	String ngoType;
	@NotNull(message = "{ngo.motive.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{ngo.motive.invalid}")
	String ngoMotive;
	@Min(value = 1000, message = "{trainee.donation.invalid}")
	@Max(value = 10000, message = "{trainee.donation.invalid}")
	double donation;
	@Min(value = 1, message = "{trainee.size.invalid}")
	@Max(value = 100, message = "{trainee.size.invalid}")
	int ngoSize;
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{ngo.activities.invalid}")
	String ngoActivities;
	TrainingCourseDTO trainingCourse;
	
	public static NGODTO entityToDTO(NGO ngo){
		NGODTO ngoDto = new NGODTO();
		ngoDto.setNgoId(ngo.getNgoId());
		ngoDto.setNgoName(ngo.getNgoName());
		ngoDto.setNgoLocation(ngo.getNgoLocation());
		ngoDto.setNgoType(ngo.getNgoType());
		ngoDto.setNgoMotive(ngo.getNgoMotive());
		ngoDto.setDonation(ngo.getDonation());
		ngoDto.setNgoSize(ngo.getNgoSize());
		ngoDto.setNgoActivities(ngo.getNgoActivities());
		TrainingCourse trainingCourse = ngo.getTrainingCourse();
		if(trainingCourse == null) {
			ngoDto.setTrainingCourse(null);
		}
		else {
			ngoDto.setTrainingCourse(TrainingCourseDTO.entityToDTO(trainingCourse));
		}
		return ngoDto;
	}

	public Integer getNgoId() {
		return ngoId;
	}

	public void setNgoId(Integer ngoId) {
		this.ngoId = ngoId;
	}

	public String getNgoName() {
		return ngoName;
	}

	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}

	public String getNgoLocation() {
		return ngoLocation;
	}

	public void setNgoLocation(String ngoLocation) {
		this.ngoLocation = ngoLocation;
	}

	public String getNgoType() {
		return ngoType;
	}

	public void setNgoType(String ngoType) {
		this.ngoType = ngoType;
	}

	public String getNgoMotive() {
		return ngoMotive;
	}

	public void setNgoMotive(String ngoMotive) {
		this.ngoMotive = ngoMotive;
	}

	public double getDonation() {
		return donation;
	}

	public void setDonation(double donation) {
		this.donation = donation;
	}

	public int getNgoSize() {
		return ngoSize;
	}

	public void setNgoSize(int ngoSize) {
		this.ngoSize = ngoSize;
	}

	public String getNgoActivities() {
		return ngoActivities;
	}

	public void setNgoActivities(String ngoActivities) {
		this.ngoActivities = ngoActivities;
	}

	public TrainingCourseDTO getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(TrainingCourseDTO trainingCourse) {
		this.trainingCourse = trainingCourse;
	}

	@Override
	public String toString() {
		return "NGO [ngoId=" + ngoId + ", ngoName=" + ngoName + ", ngoLocation=" + ngoLocation + ", ngoType=" + ngoType
				+ ", ngoMotive=" + ngoMotive + ", donation=" + donation + ", ngoSize=" + ngoSize + ", ngoActivities="
				+ ngoActivities + ", trainingCourse=" + trainingCourse + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(donation, ngoActivities, ngoId, ngoLocation, ngoMotive, ngoName, ngoSize, ngoType,
				trainingCourse);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NGODTO other = (NGODTO) obj;
		return Double.doubleToLongBits(donation) == Double.doubleToLongBits(other.donation)
				&& Objects.equals(ngoActivities, other.ngoActivities) && Objects.equals(ngoId, other.ngoId)
				&& Objects.equals(ngoLocation, other.ngoLocation) && Objects.equals(ngoMotive, other.ngoMotive)
				&& Objects.equals(ngoName, other.ngoName) && ngoSize == other.ngoSize
				&& Objects.equals(ngoType, other.ngoType); 
//				&& Objects.equals(trainingCourse, other.trainingCourse);
	}
	
	
	
}
