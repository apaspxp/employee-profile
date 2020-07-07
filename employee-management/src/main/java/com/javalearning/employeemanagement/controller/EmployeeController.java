package com.javalearning.employeemanagement.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javalearning.employeemanagement.entity.EmployeeProfile;
import com.javalearning.employeemanagement.model.ImageResponse;
import com.javalearning.employeemanagement.model.ResponseMessage;
import com.javalearning.employeemanagement.services.EmployeeProfileService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	EmployeeProfileService employeeProfileService;
	
	@RequestMapping(value = "/hello",method = RequestMethod.GET)
	public String hello() {
		System.getenv().keySet().stream().forEach(k -> System.out.println("Key: "+k));
		System.out.println(new Date());
		List<String> list = Arrays.asList("CAPITAL MARKET","ASSET & WEALTH MANAGEMENT","CORPORATE SECTOR","Test String","Filter");
		list.stream().filter(l -> l.equals(l.toUpperCase())).forEach(fl -> {
			System.out.println("Filtered elements: "+fl);
		});
		return "Hello";
	}
	
	@RequestMapping(value = "/addemployeeprofile", method =RequestMethod.POST)
	public ResponseEntity<ResponseMessage> addEmployeeProfile(@RequestBody EmployeeProfile employeeProfile) {
		ResponseMessage response = new ResponseMessage();
		response.message = employeeProfileService.persistEmployeeProfile(employeeProfile);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/uploadimage", method =RequestMethod.POST)
	public ResponseEntity<ResponseMessage> uploadProfileImage(@RequestParam("picture") MultipartFile picture, @RequestParam("employeeNumber") String employeeNumber) {
		System.out.println("Employee Number: " + employeeNumber);
		System.out.println("File Name: " +picture.getOriginalFilename());
		ResponseMessage response = new ResponseMessage();
		response.message = "Employee Number: " + employeeNumber + " File Name: " +picture.getOriginalFilename();
		employeeProfileService.persistEmployeeImage(employeeNumber, picture);
		return new ResponseEntity<ResponseMessage>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getemployee/{employeeNumber}",method = RequestMethod.GET)
	public ImageResponse getEmployeeProfile(@PathVariable String employeeNumber) {
		EmployeeProfile employeeProfile = employeeProfileService.getEmployeeProfile(employeeNumber);
		ImageResponse response = new ImageResponse();
		response.setEmployeeNumber(employeeNumber);
		response.setPicture(employeeProfile.getPicture());
		return response;
	}
}

