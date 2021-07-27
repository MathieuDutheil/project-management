package com.jrp.pma.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrp.pma.dao.EmployeeRepository;

@Service //could be replace by @Component, @Controller, @Repository
public class EmployeeService {

	// Field injection
	@Autowired
	EmployeeRepository empRepo;


	// Constructor Injection
//	public EmployeeService(EmployeeRepository empRepo) {
//		super();
//		this.empRepo = empRepo;
//	}

	//Setter Injection
//	@Autowired
//	public void setEmpRepo(EmployeeRepository empRepo) {
//		this.empRepo = empRepo;
//	}

	
	

}
