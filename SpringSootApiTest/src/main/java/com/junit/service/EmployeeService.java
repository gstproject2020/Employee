package com.junit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junit.entity.Employee;
import com.junit.repository.EmpRepository;

@Service
public class EmployeeService {

	@Autowired
	EmpRepository empRepo;

	public Employee saveEmp(Employee emp) {
		return empRepo.save(emp);
	}

	public List<Employee> findAll() {
		return empRepo.findAll();
	}
	
	public Employee finsOne(int id) {
		return empRepo.getOne(id);
	}
	
	public void delete(Employee emp) {
		empRepo.delete(emp);
	}

}
