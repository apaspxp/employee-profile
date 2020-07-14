package com.javalearning.employeemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javalearning.employeemanagement.entity.EmployeeProfile;

@Repository
public interface EmployeeProfileRepository extends JpaRepository<EmployeeProfile, Integer> {

	public EmployeeProfile findByEmployeeNumber(Integer employeeNumber);
	
	public boolean existsByFirstNameIgnoreCaseContainingAndMiddleNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndEmail(String firstName, String middleName, String lastName, String email);
	
	public List<EmployeeProfile> findByFirstNameIgnoreCaseContainingAndMiddleNameIgnoreCaseContainingAndLastNameIgnoreCaseContainingAndEmail(String firstName, String middleName, String lastName, String email);
}
