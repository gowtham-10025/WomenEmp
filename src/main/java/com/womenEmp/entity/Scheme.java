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
import com.womenEmp.dto.SchemeDTO;

@Entity
public class Scheme {

	/*
	 * @GeneratedValue is used to automatically generate the primary key value
	 * they are auto incremented.
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int schemeId;
	private String schemeName;
	private String schemeType;
	private LocalDate launchDate;
	private String schemeEligibility;
	private String schemeObjective;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "feedback_id", unique = true)
	private Feedback feedback;
	
	/*
	 * @desc -> Function to set the entity value based on DTO data
	 * @param -> schemeDTO
	 * @return -> Scheme
	 *
	 * */
	
	
	public static Scheme dtoToEntity(SchemeDTO schemesDto) {
		
		Scheme schemes = new Scheme();
		schemes.setLaunchDate(schemesDto.getLaunchDate());
		schemes.setSchemeId(schemesDto.getSchemeId());
		schemes.setSchemeName(schemesDto.getSchemeName());
		schemes.setSchemeType(schemesDto.getSchemeType());
		schemes.setSchemeEligibility(schemesDto.getSchemeEligibility());
		schemes.setSchemeObjective(schemesDto.getSchemeObjective());
		FeedbackDTO feedback = schemesDto.getFeedback();
		if(feedback == null) {
			schemes.setFeedback(null);
		}else {
			schemes.setFeedback(Feedback.dtoToEntity(feedback));
		}
		return schemes;
		
	}
	
	
	
		/**
	 * @return the schemeId
	 */
	public int getSchemeId() {
		return schemeId;
	}
	/**
	 * @param schemeId the schemeId to set
	 */
	public void setSchemeId(int schemeId) {
		this.schemeId = schemeId;
	}
	/**
	 * @return the schemeName
	 */
	public String getSchemeName() {
		return schemeName;
	}
	/**
	 * @param schemeName the schemeName to set
	 */
	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}
	/**
	 * @return the schemeType
	 */
	public String getSchemeType() {
		return schemeType;
	}
	/**
	 * @param schemeType the schemeType to set
	 */
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	/**
	 * @return the launchDate
	 */
	public LocalDate getLaunchDate() {
		return launchDate;
	}
	/**
	 * @param launchDate the launchDate to set
	 */
	public void setLaunchDate(LocalDate launchDate) {
		this.launchDate = launchDate;
	}
	/**
	 * @return the schemeEligibility
	 */
	public String getSchemeEligibility() {
		return schemeEligibility;
	}
	/**
	 * @param schemeEligibility the schemeEligibility to set
	 */
	public void setSchemeEligibility(String schemeEligibility) {
		this.schemeEligibility = schemeEligibility;
	}
	/**
	 * @return the schemeObjective
	 */
	public String getSchemeObjective() {
		return schemeObjective;
	}
	/**
	 * @param schemeObjective the schemeObjective to set
	 */
	public void setSchemeObjective(String schemeObjective) {
		this.schemeObjective = schemeObjective;
	}
	
	public Feedback getFeedback() {
		return feedback;
	}
	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}



	@Override
	public String toString() {
		return "Schemes [schemeId=" + schemeId + ", schemeName=" + schemeName + ", schemeType=" + schemeType
				+ ", LaunchDate=" + launchDate + ", schemeEligibility=" + schemeEligibility + ", schemeObjective="
				+ schemeObjective + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(launchDate, schemeEligibility, schemeId, schemeName, schemeObjective, schemeType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scheme other = (Scheme) obj;
		return Objects.equals(launchDate, other.launchDate)
				&& Objects.equals(schemeEligibility, other.schemeEligibility) && schemeId == other.schemeId
				&& Objects.equals(schemeName, other.schemeName)
				&& Objects.equals(schemeObjective, other.schemeObjective)
				&& Objects.equals(schemeType, other.schemeType);
	}


	

}