package com.junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.junit.entity.Employee;
import com.junit.repository.EmpRepository;
import com.junit.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringSootApiTestApplicationTests {

	@Autowired
	private EmployeeService service;

	@MockBean
	private EmpRepository repo;

	@Test
	public void findAll() {
		when(repo.findAll()).thenReturn(Stream.of(new Employee(100, "tej", 26, "Senior Software Engineer"),
				new Employee(100, "tej", 26, "Senior Software Engineer")).collect(Collectors.toList()));
		assertEquals(2, service.findAll().size());
	}

	@Test
	public void saveEmployee() {
		Employee emp = new Employee(100, "tej", 26, "Senior Software Engineer");
		when(repo.save(emp)).thenReturn(emp);
		assertEquals(emp, repo.save(emp));
	}

//	@Test
//	public void getEmployee() {
//		int empid=100;
//		when(repo.findById(100)).thenReturn(Stream.of(new Employee(102,"abi",28,"senior software engineer").collect(Collectors)));
//	}
//
//	@Test
//	public void updateEmployee() {
//		Employee emp=new Em()
//	}
//	
	@Test
	public void deleteEmployee() {
		Employee emp = new Employee(100, "tej", 26, "Senior Software Engineer");
		service.delete(emp);
		verify(repo, times(1)).delete(emp);

	}

}
