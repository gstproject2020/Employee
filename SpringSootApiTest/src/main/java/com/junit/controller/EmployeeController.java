package com.junit.controller;

import java.util.List;

import javax.validation.Valid;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.junit.entity.Employee;
import com.junit.service.EmployeeService;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
	//To save an employee into the database
	@PostMapping("/saveEmp")
	public Employee saveEmployee(@Valid @RequestBody Employee emp) {
		return empService.saveEmp(emp);
	}
	
	//To get all the employee list
	@GetMapping("/getEmp")
	public List<Employee> getEmployees() {
		return empService.findAll();
	}
	
	//To get an employee
	@GetMapping("/getEmpById/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable(value="id") int id){
		
		Employee emp = empService.finsOne(id);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
		
	}
	
	//To update an employee by id
	@PutMapping("/updateEmp")
	public ResponseEntity<Employee> updateEmployee( @RequestBody Employee empDetails){
		
		Employee emp = empService.finsOne(empDetails.getEmpId());
		
		if(emp == null) {
			return ResponseEntity.notFound().build();
		}
		
		emp.setName(empDetails.getName());
		emp.setAge(empDetails.getAge());	
		emp.setDesignation(empDetails.getDesignation());
		
		Employee updatedEmployee = empService.saveEmp(emp);
		
		return ResponseEntity.ok().body(updatedEmployee);
		
	}
	
	//Delete an employee by id
	@DeleteMapping("/deleteEmp/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable(value = "id") int id){
		
		Employee emp = empService.finsOne(id);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		empService.delete(emp);
		return ResponseEntity.ok().build();
	}
	
	
}