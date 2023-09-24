package com.finzly.bbcops.services;

import java.util.List;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Random;

import com.finzly.bbcops.dao.EmployeeDao;
import com.finzly.bbcops.entities.Employee;
import com.finzly.bbcops.util.mail.MailSenderService;

@Service
public class EmployeeService {

	private final EmployeeDao employeeDao;
	private final MailSenderService mailService;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	public EmployeeService(EmployeeDao employeeDao, MailSenderService mailService) {
		this.employeeDao = employeeDao;
		this.mailService = mailService;
	}

	public List<Employee> getAllEmployees() {
		return employeeDao.getEmployees();
	}

	public Employee getEmployeeById(long employeeId) {
		return employeeDao.getEmployeeById(employeeId);
	}

	public ResponseEntity<Map<String, Object>> loginEmployee(long employeeId, String otp) {
		Map<String, Object> response = new HashMap<>();
		Employee employee = employeeDao.getEmployeeById(employeeId);

		if (employee != null) {
			if (otp != null && otp.equals(employee.getOtp())) {
				employee.setOtp(null);
				employeeDao.updateEmployee(employee);
				response.put("isValid", true);
				response.put("employee", employee);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.put("isValid", false);
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}
		} else {
			response.put("isValid", false);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}

	public String generateOtp() {
		Random random = new Random();
		int otpNumber = 100000 + random.nextInt(900000);
		return String.valueOf(otpNumber);
	}

	public ResponseEntity<String> sendOtpForLogin(long employeeId) {

		String otp = generateOtp();
		if (employeeDao.doesEmployeeExist(employeeId)) {
			Employee employee = getEmployeeById(employeeId);
			employee.setOtp(otp);
			updateEmployee(employee);
			mailService.sendOtp(employee, otp);
			logger.info("otp sent");
			return new ResponseEntity<>(otp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Employee Not Exist", HttpStatus.OK);
		}
	}

	public void updateEmployee(Employee employee) {
		employeeDao.updateEmployee(employee);
	}
}
