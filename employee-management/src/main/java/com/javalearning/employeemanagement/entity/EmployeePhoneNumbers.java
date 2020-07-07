package com.javalearning.employeemanagement.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class EmployeePhoneNumbers {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	
	private String phoneNumber;
	
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinColumn(name = "EMPLOYEE_NUMBER")
//	@JsonBackReference
	private EmployeeProfile employeeProfile;

	public EmployeePhoneNumbers() {
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public EmployeeProfile getEmployeeProfile() {
		return employeeProfile;
	}

	public void setEmployeeProfile(EmployeeProfile employeeProfile) {
		this.employeeProfile = employeeProfile;
	}

	@Override
	public String toString() {
		return "EmployeePhoneNumbers [phoneNumber=" + phoneNumber + "]";
	}
	
}
