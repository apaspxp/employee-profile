package com.javalearning.employeemanagement.services;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.javalearning.employeemanagement.entity.EmployeeProfile;
import com.javalearning.employeemanagement.repository.EmployeePhoneNumbersRepository;
import com.javalearning.employeemanagement.repository.EmployeeProfileRepository;

@Service
public class EmployeeProfileService {

	@Autowired
	EmployeePhoneNumbersRepository employeePhoneNumbersRepository;
	
	@Autowired
	EmployeeProfileRepository employeeProfileRepository;
	
	@Transactional(rollbackFor = Exception.class)
	public String persistEmployeeProfile(EmployeeProfile employeeProfile) {
		
//		if (!employeeProfileRepository.existsById(employeeProfile.getEmployeeNumber())) {
		if (!employeeProfileRepository.existsByFirstNameIgnoreCaseContainingAndMiddleNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndEmail(employeeProfile.getFirstName(), employeeProfile.getMiddleName(), employeeProfile.getLastName(), employeeProfile.getEmail())) {
			EmployeeProfile employeeProfileObj = new EmployeeProfile();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.UK);
			employeeProfileObj.setEmployeeNumber(employeeProfile.getEmployeeNumber());
			employeeProfileObj.setFirstName(employeeProfile.getFirstName());
			employeeProfileObj.setMiddleName(employeeProfile.getMiddleName());
			employeeProfileObj.setLastName(employeeProfile.getLastName());
			employeeProfileObj.setEmail(employeeProfile.getEmail());
			employeeProfileObj.setPan(employeeProfile.getPan());
			employeeProfileObj.setAadhaar(employeeProfile.getAadhaar());
			employeeProfileObj.setVoterId(employeeProfile.getVoterId());
			try {
				System.out.println("Within service method date of birth: "+employeeProfile.getDateOfBirth());
				employeeProfileObj.setDateOfBirth(sdf.parse(employeeProfile.getDateOfBirth()).toString());
				employeeProfileObj.setDateOfJoining(sdf.parse(employeeProfile.getDateOfJoining()).toString());
				employeeProfileObj.setLastWorkingDate(sdf.parse(employeeProfile.getLastWorkingDate()).toString());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			employeeProfile.getPhoneNumbers().stream().forEach(p -> {
				employeeProfileObj.getPhoneNumbers().add(p);
				p.setEmployeeProfile(employeeProfileObj);
			});
			employeeProfile.getAddresses().stream().forEach(a -> {
				employeeProfileObj.getAddresses().add(a);
				a.setEmployeeProfile(employeeProfileObj);
			});
			try {
				employeeProfileRepository.save(employeeProfileObj);
				return "Saved";
			}catch (Exception e) {
				throw e;
			}
		}else {
			return "The record already exists.";
		}
		
	}
	
	public String persistEmployeeImage(String employeeNumber, MultipartFile picture) {
		if (employeeProfileRepository.existsById(Integer.parseInt(employeeNumber))) {
			EmployeeProfile employeeProfileObj = employeeProfileRepository.findByEmployeeNumber(Integer.parseInt(employeeNumber));
			try {
				byte[] bytes = picture.getBytes();
				employeeProfileObj.setPicture(bytes);
				employeeProfileRepository.save(employeeProfileObj);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "Image uploaded successfully";
		}else {
			return "The employee for employee number : "+ employeeNumber + "does not exist.";
		}
	}
	
	public EmployeeProfile getEmployeeProfile(String employeeNumber) {
		if (employeeProfileRepository.existsById(Integer.parseInt(employeeNumber))) {
			EmployeeProfile employeeProfileObj = employeeProfileRepository.findByEmployeeNumber(Integer.parseInt(employeeNumber));
			return employeeProfileObj;
		}
		return null;
	}
}
