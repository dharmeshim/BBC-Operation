package com.finzly.bbcops.util.constants;

public class EmailConstants {
	public static final String OTP_EMAIL_SUBJECT = "BBC Employee Login OTP";

	public static final String OTP_EMAIL_MESSAGE_PREFIX = "Hello,\n\n"
			+ "Your One-Time Password (OTP) for BBC employee login is:\n";

	public static final String OTP_EMAIL_MESSAGE_SUFFIX = "\n\n" + "Please use this OTP to log in to your account.\n\n"
			+ "If you didn't request this OTP, please ignore this email.\n\n"
			+ "Best regards,\n" 
			+ "BBC Support Team";

	public static final String CUSTOMER_ADDED_EMAIL_SUBJECT = "Customer Added Confirmation";
	public static final String CUSTOMER_ADDED_EMAIL_MESSAGE_PREFIX = "Dear customer,\n\n";
	public static final String CUSTOMER_ADDED_EMAIL_MESSAGE_SUFFIX = "\n\nThank you for using our service.\n\nBest regards,\nBharat Bijili Corporation (BBC)";

}
