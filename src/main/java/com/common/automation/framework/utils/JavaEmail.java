package com.common.automation.framework.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The class sends mail to the specified senders
 * 
 * @author vibhor
 *
 */
public class JavaEmail {

	Properties emailProperties;
	Session mailSession;
	MimeMessage emailMessage;
	private static Logging logger = new Logging();

	/**
	 * 
	 * @param message      - Message to be sent
	 * @param ExecutionKey - Key of Test Execution to be sent in subject
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public void sendMail(String message, String ExecutionKey) throws AddressException, MessagingException {

		try {
			JavaEmail javaEmail = new JavaEmail();

			javaEmail.setMailServerProperties();
			javaEmail.createEmailMessage(message, ExecutionKey);
			javaEmail.sendEmail();
			logger.logInfo("Email sent successfully");
		} catch (Exception e) {

			logger.logError("Error in sending email", e);
		}
	}

	private void setMailServerProperties() {

		String emailPort = "587";

		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", emailPort);
		emailProperties.put("mail.smtp.auth", "true");
		emailProperties.put("mail.smtp.starttls.enable", "true");

	}

	private void createEmailMessage(String message, String ExecutionKey) throws AddressException, MessagingException {
		String[] toEmails = { "vibhor.goyal@mydesq.com" };
		String emailSubject = "Automation Report - " + ExecutionKey;
		String emailBody = message;

		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(emailBody, "text/html");// for a html email
		// emailMessage.setText(emailBody);// for a text email

	}

	private void sendEmail() throws AddressException, MessagingException {

		String emailHost = "smtp.gmail.com";
		String fromUser = "mydesqautomaion@gmail.com";
		String fromUserEmailPassword = "mydesq@123";

		Transport transport = mailSession.getTransport("smtp");

		transport.connect(emailHost, fromUser, fromUserEmailPassword);
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();
	}
}