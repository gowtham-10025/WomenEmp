package com.womenEmp.dto;

import java.time.LocalDate;

import java.util.Objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.womenEmp.entity.Feedback;
import com.womenEmp.entity.Scheme;


public class SchemeDTO {

	private int schemeId;
	@NotNull(message = "{scheme.name.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{scheme.name.invalid}")
	private String schemeName;
	@NotNull(message = "{scheme.type.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{scheme.type.invalid}")
	private String schemeType;
	@PastOrPresent(message = "{scheme.launchdate.invalid}")
	private LocalDate LaunchDate;
	@NotNull(message = "{scheme.eligibility.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{scheme.eligibility.invalid}")
	private String schemeEligibility;
	@NotNull(message = "{scheme.objective.absent}")
	@Pattern(regexp = "[a-zA-Z]+( [A-Za-z]+)*", message = "{scheme.objective.invalid}")
	private String schemeObjective;

	private FeedbackDTO feedback;
	
public static SchemeDTO entityToDTO(Scheme schemes) {
		SchemeDTO schemesDTO = new SchemeDTO();
		schemesDTO.setLaunchDate(schemes.getLaunchDate());
		schemesDTO.setSchemeId(schemes.getSchemeId());
		schemesDTO.setSchemeName(schemes.getSchemeName());
		schemesDTO.setSchemeEligibility(schemes.getSchemeEligibility());
		schemesDTO.setSchemeObjective(schemes.getSchemeObjective());
		schemesDTO.setSchemeType(schemes.getSchemeType());
		
		Feedback feedback = schemes.getFeedback();
		if(feedback == null) {
			schemesDTO.setFeedback(null);
		}else {
			schemesDTO.setFeedback(FeedbackDTO.entityToDTO(feedback));
		}
		
		return schemesDTO;
	}
	
	public int getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(int schemeId) {
		this.schemeId = schemeId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public LocalDate getLaunchDate() {
		return LaunchDate;
	}

	public void setLaunchDate(LocalDate launchDate) {
		LaunchDate = launchDate;
	}

	public String getSchemeEligibility() {
		return schemeEligibility;
	}

	public void setSchemeEligibility(String schemeEligibility) {
		this.schemeEligibility = schemeEligibility;
	}

	public String getSchemeObjective() {
		return schemeObjective;
	}

	public void setSchemeObjective(String schemeObjective) {
		this.schemeObjective = schemeObjective;
	}

	public FeedbackDTO getFeedback() {
		return feedback;
	}

	public void setFeedback(FeedbackDTO feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "SchemesDTO [schemeId=" + schemeId + ", schemeName=" + schemeName + ", schemeType=" + schemeType
				+ ", LaunchDate=" + LaunchDate + ", schemeEligibility=" + schemeEligibility + ", schemeObjective="
				+ schemeObjective + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(LaunchDate, schemeEligibility, schemeId, schemeName, schemeObjective, schemeType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SchemeDTO other = (SchemeDTO) obj;
		return Objects.equals(LaunchDate, other.LaunchDate)
				&& Objects.equals(schemeEligibility, other.schemeEligibility) && schemeId == other.schemeId
				&& Objects.equals(schemeName, other.schemeName)
				&& Objects.equals(schemeObjective, other.schemeObjective)
				&& Objects.equals(schemeType, other.schemeType);
	}

}