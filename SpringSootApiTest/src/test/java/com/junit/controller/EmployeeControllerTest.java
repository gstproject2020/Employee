package com.junit.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.junit.entity.Employee;
import com.junit.repository.EmpRepository;
import com.junit.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

	@InjectMocks
	EmployeeController empController;

	@Mock
	EmpRepository empRepo;
	
	@Mock
	EmployeeService empService;


	@Test
	void testGetEmployees() throws URISyntaxException {

//		Synchronous client to perform HTTP requests, exposing a simple, 
//		template method API over underlying HTTP client libraries such as the JDK HttpURLConnection, Apache HttpComponents, and others.
//		The RestTemplate offers templates for common scenarios by HTTP method, 
//		in addition to the generalized exchange and execute methods that support of less frequent cases. 
//		
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:" + 8080 + "/emp" + "/getEmp";
		java.net.URI uri = new java.net.URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
//		    Assert.assertEquals(true, result.getBody().contains("employeeList"));

	}

	@Test
	void testGetEmpById() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + 8080 + "/emp" + "/getEmpById/100";
		
		URI uri=new URI(baseUrl);
		try {
		ResponseEntity<String> result=restTemplate.getForEntity(uri,String.class);
		}catch(HttpClientErrorException ex) {
			Assert.assertEquals(200,ex.getResponseBodyAsString());
		}
	}

	@Test
	void testUpdateEmployee() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + 8080 + "/emp" + "/updateEmp";
	
		URI uri=new URI(baseUrl);
		
		Employee emp=new Employee(100,"basava",28,"Senior Software Dev");
		HttpHeaders headers =new HttpHeaders();
		HttpEntity<Employee> request = new HttpEntity<Employee>(emp, headers);
		try {
			restTemplate.put(uri, request);
		}catch(HttpClientErrorException ex) {
			Assert.assertEquals(200,ex.getResponseBodyAsString());
		}
	}

	@Test
	void testDeleteEmployee() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + 8080 + "/emp" + "/getEmpById/111";
		
		URI uri=new URI(baseUrl);
		
		try {
			restTemplate.delete(uri);
			}catch(Exception ex) {
				
			}
		
	}

	@Test
	public void testAddEmployee() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + 8080 + "/emp" + "/saveEmp";
		URI uri = new URI(baseUrl);
		
		Employee employee = new Employee(111, "Nimesh", 31, "Angular Developer");

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<Employee> request = new HttpEntity<Employee>(employee, headers);

		try {
			restTemplate.postForEntity(uri, request, String.class);
		} catch (HttpClientErrorException ex) {
			// Verify bad request and missing header
			Assert.assertEquals(400, ex.getRawStatusCode());
			//Assert.assertEquals(true, ex.getResponseBodyAsString().contains("Missing request header"));
		}
	}

}