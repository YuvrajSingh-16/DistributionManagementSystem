package com.telcomdms.service;

public interface EmailService {
	boolean sendSimpleEmail(String to, String subject, String message);
}
