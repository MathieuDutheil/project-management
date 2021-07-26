package com.jrp.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository empRepo;

	@GetMapping
	public String displayEmployees(Model model) {
		
		// we are querying the database for employees
		List<Employee> employees = empRepo.findAll();
		model.addAttribute("employees", employees);
		return "employees/list-employees";
	}

	@GetMapping("/new")
	public String displayEmployeeForm(Model model) {

		Employee anEmployee = new Employee();
		
		model.addAttribute("employee", anEmployee);

		return "employees/new-employee.html"; // .html is optional, you can type "new-employee" alone Thymeleaf is smart
												// enough to map by name
	}

	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		// save to the database using an employee crud repository
		empRepo.save(employee);

		// use a redirect to prevent duplicate submissions
		return "redirect:/employees/new";
	}
}