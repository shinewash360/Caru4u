package Shinewash360.ShineWash360.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class ForgotEmailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    public void sendEmail(String to, String subject, String message) {


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
    }
}
