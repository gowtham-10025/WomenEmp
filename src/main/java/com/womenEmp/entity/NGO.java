package com.womenEmp.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.womenEmp.dto.NGODTO;
import com.womenEmp.dto.TrainingCourseDTO;

@Entity
public class NGO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer ngoId;
	String ngoName;
	String ngoLocation;
	String ngoType;
	String ngoMotive;
	double donation;
	int ngoSize;
	String ngoActivities;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "training_course_id", unique = true)
	TrainingCourse trainingCourse;
	
	public static NGO dtoToEntity(NGODTO ngoDto){
		NGO ngo = new NGO();
		ngo.setNgoId(ngoDto.getNgoId());
		ngo.setNgoName(ngoDto.getNgoName());
		ngo.setNgoLocation(ngoDto.getNgoLocation());
		ngo.setNgoType(ngoDto.getNgoType());
		ngo.setNgoMotive(ngoDto.getNgoMotive());
		ngo.setDonation(ngoDto.getDonation());
		ngo.setNgoSize(ngoDto.getNgoSize());
		ngo.setNgoActivities(ngoDto.getNgoActivities());
		
		TrainingCourseDTO trainingCourse = ngoDto.getTrainingCourse();
		if(trainingCourse == null) {
			ngo.setTrainingCourse(null);
		}
		else {
			ngo.setTrainingCourse(TrainingCourse.dtotoEntity(trainingCourse));
		}
		return ngo;
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

	public TrainingCourse getTrainingCourse() {
		return trainingCourse;
	}

	public void setTrainingCourse(TrainingCourse trainingCourse) {
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
		NGO other = (NGO) obj;
		return Double.doubleToLongBits(donation) == Double.doubleToLongBits(other.donation)
				&& Objects.equals(ngoActivities, other.ngoActivities) && Objects.equals(ngoId, other.ngoId)
				&& Objects.equals(ngoLocation, other.ngoLocation) && Objects.equals(ngoMotive, other.ngoMotive)
				&& Objects.equals(ngoName, other.ngoName) && ngoSize == other.ngoSize
				&& Objects.equals(ngoType, other.ngoType); 
//				&& Objects.equals(trainingCourse, other.trainingCourse);
	}
	
	
	
}
