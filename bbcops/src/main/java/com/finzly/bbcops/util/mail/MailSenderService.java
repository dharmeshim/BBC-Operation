package com.finzly.bbcops.util.mail;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.finzly.bbcops.entities.Customer;
import com.finzly.bbcops.entities.Employee;
import com.finzly.bbcops.util.constants.EmailConstants;

@Service
public class MailSenderService {
	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(String toMail, String Subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("barathbijili@gmail.com");
		message.setTo(toMail);
		message.setText(body);
		message.setSubject(Subject);
		mailSender.send(message);
	}

	public void sendOtp(Employee employee, String otp) {
		String email = employee.getEmailAddress();
		String subject = EmailConstants.OTP_EMAIL_SUBJECT;
		String message = EmailConstants.OTP_EMAIL_MESSAGE_PREFIX 
				+ otp 
				+ EmailConstants.OTP_EMAIL_MESSAGE_SUFFIX;
		sendMail(email, subject, message);
	}

	public void sendMailAboutCustomerAdded(Customer customer) {
		String email = customer.getEmail();
		String subject = EmailConstants.CUSTOMER_ADDED_EMAIL_SUBJECT;
		String message = EmailConstants.CUSTOMER_ADDED_EMAIL_MESSAGE_PREFIX
				+ "Your customer account has been successfully added.\n" 
				+ "Customer ID: " 
				+ customer.getCustomerId()
				+ "\n" + EmailConstants.CUSTOMER_ADDED_EMAIL_MESSAGE_SUFFIX;
		sendMail(email, subject, message);
	}

}
