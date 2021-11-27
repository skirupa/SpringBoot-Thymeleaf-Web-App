package com.example.demo.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import com.example.demo.model.Email;

@Service
public class EmailService {
	@Autowired
	private Email email;
	
	private Session session;
	@Autowired
	JavaMailSenderImpl mailSender;
	

	public EmailService(Email email) {
		super();
		this.email = email;
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		session = Session.getDefaultInstance(properties);
	}
	public void sendSimpleEmail() {
		String host = "smtp.gmail.com";
		String from = email.getFrom();
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", "lhpjwqprtryhysex");
		props.put("mail.smtp.port", "465"); 
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.ssl.enable", true);
		try{
		    Session session = Session.getDefaultInstance(props, null);
		    MimeMessage message = new MimeMessage(session);
		    message.setFrom(new InternetAddress(from));
		    message.addRecipients(Message.RecipientType.TO, email.getTo());
		    message.setSubject(email.getSubject());
		    message.setText(email.getBody());
		    Transport transport = session.getTransport("smtp");
		    transport.connect("smtp.gmail.com", email.getFrom(), "lhpjwqprtryhysex");//CAUSES EXCEPTION
		    transport.sendMessage(message, message.getAllRecipients());
		}catch(MessagingException e){
		    e.printStackTrace();
		}
		System.out.println("Mail Sent...");
	}
	
}
