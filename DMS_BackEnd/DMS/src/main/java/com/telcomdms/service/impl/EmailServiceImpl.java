package com.telcomdms.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.telcomdms.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
	
	private Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

	@Override
	public boolean sendSimpleEmail(String to, String subject, String message) {
		try {
			logger.info("[+] Sending data by mail");
			
			ExecutorService emailExecutor = Executors.newCachedThreadPool();
			
	        emailExecutor.execute(() -> {
	                sendEmail(message, subject, to);
	        });
			
			logger.info("[+] Mail Sent..");
			return true;
		} catch(Exception e) {
			logger.warn("[-] Sending mail interrupted");
		}
		return false;
	}
	
	private boolean sendEmail(String message, String subject, String to) {
		String from = "application.dms2022@gmail.com";
		
		boolean flag = false;
		
		// Variable for Gmail
		String host = "smtp.gmail.com";
		
		// Get the system properties
		Properties properties = System.getProperties();
		
		// Setting important information to properties object
		
		// Host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		// Step 1: to get the session object..
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, "DMS@2022");
			}
		});
		
		// Step 2: Compose the message [text, multiMedia]
		MimeMessage m = new MimeMessage(session);
		
		try {
			m.setFrom(from);
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			m.setContent(message, "text/html");
			
			// Step 3: Send the message using Transport class
			Transport.send(m);
			flag = true;
			logger.info("[+] Mail Sent..");
		}catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return flag;
	}

}
