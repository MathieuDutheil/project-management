package com.jrp.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.dao.ProjectRepository;
import com.jrp.pma.dto.ChartData;
import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Project;
import com.jrp.pma.springExample.Car;

@Controller
public class HomeController {
	
	@Autowired
	Car car;
	
	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	
	@GetMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		
		Map<String, Object> map = new HashMap<>();
		
		// we are querying the database for projects
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectsList", projects);
		
		// we are querying the database to count project status
		List<ChartData> projectData = proRepo.getProjectStatus();
		
		// Let's convert projectData object into a json structure for use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(projectData);
		// [["NOTSTARTED", 1] , ["INPROGRESS", 2] , ["COMPLETED", 1]]
		model.addAttribute("projectStatusCount", jsonString);
		
		// we are querying the database for list of project by employees
		List<EmployeeProject> employeesProjectCount = empRepo.employeeProjects();
		model.addAttribute("employeesListProjectsCount", employeesProjectCount);
		

		
		
		
		
		
		return "main/home";
	}

}
