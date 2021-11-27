package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Email;
import com.example.demo.model.Employee;
import com.example.demo.service.EmailService;
import com.example.demo.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("listEmployees",employeeService.getAllEmployees());
		return "index";
	}
	
	@GetMapping("/showNewEmployeeForm")
	public String showNewEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "new_employee";
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}
	
	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable (value="id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmploye(@PathVariable (value= "id") long id) {
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	@GetMapping("/showEmailForm/{id}")
	public String showEmailForm(@PathVariable (value="id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		Email email = new Email();
		email.setFirstName(employee.getFirstName());
		email.setLastName(employee.getLastName());
		email.setTo(employee.getEmail());
		email.setFrom("pettaparaak003@gmail.com");
		model.addAttribute("email", email);
		return "email_form";
	}
	@PostMapping("/sendEmail")
	public String sendEmail(@ModelAttribute("email") Email email) {
		EmailService emailService = new EmailService(email);
		emailService.sendSimpleEmail();
		return "redirect:/";
	}
}
