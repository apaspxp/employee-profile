package com.javalearning.employeemanagement.model;

import java.util.ArrayList;
import java.util.List;


public class EmployeeProfileModel {

	public Integer employeeNumber;
	
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
	
	public byte[] picture;
	
	public List<EmployeeAddressModel> addresses = new ArrayList<>();
	
	public List<EmployeePhoneNumberModel> phoneNumbers = new ArrayList<>();
	
	public EmployeeProfileModel() {
	}

	public EmployeeProfileModel(Integer employeeNumber, String firstName, String middleName, String lastName,
			String email, String pan, String aadhaar, String voterId, String dateOfBirth, String dateOfJoining,
			String lastWorkingDate, byte[] picture, List<EmployeeAddressModel> addresses,
			List<EmployeePhoneNumberModel> phoneNumbers) {
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
	
	
}
