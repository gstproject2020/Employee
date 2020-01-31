package com.junit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.junit.entity.Employee;

@Repository
@Transactional
public interface EmpRepository extends JpaRepository<Employee, Integer> {
	
	
}
