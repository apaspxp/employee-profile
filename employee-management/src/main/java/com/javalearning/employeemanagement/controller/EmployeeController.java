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
import com.javalearning.employeemanagement.model.EmployeeAddressModel;
import com.javalearning.employeemanagement.model.EmployeePhoneNumberModel;
import com.javalearning.employeemanagement.model.EmployeeProfileModel;
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
//		This is a comment
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
	
	@RequestMapping(value = "/updateemployeeprofile", method =RequestMethod.PUT)
	public ResponseEntity<ResponseMessage> updateEmployeeProfile(@RequestBody EmployeeProfile employeeProfile) {
		ResponseMessage response = new ResponseMessage();
		response.message = employeeProfileService.updateEmployeeProfile(employeeProfile);
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
	
	@RequestMapping(value = "/getemployeeimage/{employeeNumber}",method = RequestMethod.GET)
	public ImageResponse getEmployeeProfileImage(@PathVariable String employeeNumber) {
		EmployeeProfile employeeProfile = employeeProfileService.getEmployeeProfile(employeeNumber);
		ImageResponse response = new ImageResponse();
		response.setEmployeeNumber(employeeNumber);
		response.setPicture(employeeProfile.getPicture());
		return response;
	}
	
	@RequestMapping(value = "/getemployee/{employeeNumber}",method = RequestMethod.GET)
	public EmployeeProfileModel getEmployeeProfile(@PathVariable String employeeNumber) {
		EmployeeProfile employeeProfile = employeeProfileService.getEmployeeProfile(employeeNumber);
		if (employeeProfile != null) {
			List<EmployeeAddressModel> addressModels = new ArrayList<>();
			
			List<EmployeePhoneNumberModel> phoneNumbers = new ArrayList<>();
			employeeProfile.getAddresses().stream().forEach(a -> {
				EmployeeAddressModel addressModel = new EmployeeAddressModel(a.getId(), a.getAddressLine(), a.getStreet(), a.getCity(), a.getState(), a.getPinCode(), a.getCountry());
				addressModels.add(addressModel);
			});
			employeeProfile.getPhoneNumbers().stream().forEach(p -> {
				EmployeePhoneNumberModel phone = new EmployeePhoneNumberModel(p.getId(), p.getPhoneNumber());
				phoneNumbers.add(phone);
			});
			
			EmployeeProfileModel employee = new EmployeeProfileModel(employeeProfile.getEmployeeNumber(), employeeProfile.getFirstName(), employeeProfile.getMiddleName(), employeeProfile.getLastName(), employeeProfile.getEmail(), employeeProfile.getPan(), employeeProfile.getAadhaar(), employeeProfile.getAadhaar(), employeeProfile.getDateOfBirth(), employeeProfile.getDateOfJoining(), employeeProfile.getLastWorkingDate(), employeeProfile.getPicture(), addressModels, phoneNumbers);
			return employee;
		}else {
			EmployeeProfileModel emptyEmployee = new EmployeeProfileModel(0, "", "", "", "", "", "", "", "", "", "", null, null, null);
			return emptyEmployee;
		}
	}
	
	@RequestMapping(value = "/getallemployees",method = RequestMethod.GET)
	public List<EmployeeProfileModel> getAllEmployees(){
		List<EmployeeProfile> employees = employeeProfileService.getAllEmployees();
		List<EmployeeProfileModel> employeesModel = new ArrayList<>();
		
		if (employees.size() > 0) {
			employees.stream().forEach(e -> {
				List<EmployeeAddressModel> addressModels = new ArrayList<>();
				List<EmployeePhoneNumberModel> phoneNumbers = new ArrayList<>();
				
				e.getAddresses().stream().forEach(a -> {
					EmployeeAddressModel addressModel = new EmployeeAddressModel(a.getId(), a.getAddressLine(), a.getStreet(), a.getCity(), a.getState(), a.getPinCode(), a.getCountry());
					addressModels.add(addressModel);
				});
				e.getPhoneNumbers().stream().forEach(p -> {
					EmployeePhoneNumberModel phone = new EmployeePhoneNumberModel(p.getId(), p.getPhoneNumber());
					phoneNumbers.add(phone);
				});
				EmployeeProfileModel employeeProfileModel = new EmployeeProfileModel(e.getEmployeeNumber(), e.getFirstName(), e.getMiddleName(), e.getLastName(), e.getEmail(), e.getPan(), e.getAadhaar(), e.getAadhaar(), e.getDateOfBirth(), e.getDateOfJoining(), e.getLastWorkingDate(), e.getPicture(), addressModels, phoneNumbers);
				employeesModel.add(employeeProfileModel);
			});
			return employeesModel;
		}else {
			return Arrays.asList(new EmployeeProfileModel(0, "", "", "", "", "", "", "", "", "", "", null, null, null));
		}
	}
}

