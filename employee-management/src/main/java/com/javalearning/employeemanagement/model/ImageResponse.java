package com.javalearning.employeemanagement.model;

public class ImageResponse {
	public String employeeNumber;
	public byte[] picture;
	
	public ImageResponse() {
	}
	
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	
	public byte[] getPicture() {
		return picture;
	}
	
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
}
