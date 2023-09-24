package com.finzly.bbcops.controllers;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.finzly.bbcops.entities.Employee;
import com.finzly.bbcops.services.EmployeeService;

@CrossOrigin()
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	

	//send OTP for login
	@GetMapping("/login/send-otp/{employeeId}")
	public ResponseEntity<String> loginEmployee(@PathVariable long employeeId) {
		return employeeService.sendOtpForLogin(employeeId);
	}

	//verify OTP entered user
	@PostMapping("/login/verify")
	public ResponseEntity<Map<String, Object>> verifyOtp(@RequestBody Employee employee) {
		long employeeId = employee.getEmployeeId();
		String otp = employee.getOtp();
		return employeeService.loginEmployee(employeeId, otp);
	}

	//get employee by employee ID
	@GetMapping("/get-employee/{employeeId}")
	public Employee getEmployee(@PathVariable long employeeId) {
		return employeeService.getEmployeeById(employeeId);
	}
}
