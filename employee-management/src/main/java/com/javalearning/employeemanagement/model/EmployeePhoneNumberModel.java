package com.javalearning.employeemanagement.model;

public class EmployeePhoneNumberModel {

	private int Id;
	
	private String phoneNumber;

	public EmployeePhoneNumberModel(int id, String phoneNumber) {
		Id = id;
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
