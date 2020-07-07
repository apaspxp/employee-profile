package com.javalearning.employeemanagement.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class EmployeeProfile implements Serializable {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = -368531962111344994L;

	@Id
	@Column(name = "EMPLOYEE_NUMBER")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int employeeNumber;
	
	public String firstName;
	
	public String middleName;
	
	public String lastName;
	
	public String email;
	
	public String pan;
	
	public String aadhaar;
	
	public String voterId;
	
	public String dateOfBirth;
	
	public String dateOfJoining;
	
	public String lastWorkingDate;
	
	@Lob
	@JsonIgnore
	public byte[] picture;
	
	@OneToMany(mappedBy = "employeeProfile", cascade = CascadeType.ALL)
	public List<EmployeeAddress> addresses = new ArrayList<>();
	
	@OneToMany(mappedBy = "employeeProfile", cascade = CascadeType.ALL)
	public List<EmployeePhoneNumbers> phoneNumbers = new ArrayList<>();
	
//	->address
//	->pan card,addhar,voter id,Date of birth
//	->joining date
//	->last working date
//	->photo
	public EmployeeProfile() {
	}

	public EmployeeProfile(int employeeNumber, String firstName, String middleName, String lastName, String email,
		String pan, String aadhaar, String voterId, String dateOfBirth, String dateOfJoining, String lastWorkingDate,
		@JsonProperty("picture") byte[] picture, List<EmployeeAddress> addresses, List<EmployeePhoneNumbers> phoneNumbers) {
	this.employeeNumber = employeeNumber;
	this.firstName = firstName;
	this.middleName = middleName;
	this.lastName = lastName;
	this.email = email;
	this.pan = pan;
	this.aadhaar = aadhaar;
	this.voterId = voterId;
	this.dateOfBirth = dateOfBirth;
	this.dateOfJoining = dateOfJoining;
	this.lastWorkingDate = lastWorkingDate;
	this.picture = picture;
	this.addresses = addresses;
	this.phoneNumbers = phoneNumbers;
	}

	public int getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getLastWorkingDate() {
		return lastWorkingDate;
	}

	public void setLastWorkingDate(String lastWorkingDate) {
		this.lastWorkingDate = lastWorkingDate;
	}

	@JsonProperty
	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public List<EmployeeAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<EmployeeAddress> addresses) {
		this.addresses = addresses;
	}

	public List<EmployeePhoneNumbers> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(List<EmployeePhoneNumbers> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	@Override
	public String toString() {
		return "EmployeeProfile [employeeNumber=" + employeeNumber + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", pan=" + pan + ", aadhaar=" + aadhaar + ", voterId="
				+ voterId + ", dateOfBirth=" + dateOfBirth + ", dateOfJoining=" + dateOfJoining + ", lastWorkingDate="
				+ lastWorkingDate + ", addresses=" + addresses + ", phoneNumbers=" + phoneNumbers + "]";
	}

}
