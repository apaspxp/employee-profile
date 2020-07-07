package com.javalearning.employeemanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javalearning.employeemanagement.entity.EmployeePhoneNumbers;

@Repository
public interface EmployeePhoneNumbersRepository extends JpaRepository<EmployeePhoneNumbers, Integer> {

}
