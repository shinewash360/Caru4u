package Caru4u.Caru4u.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ForgotEmailService {
//    @Autowired
//    private JavaMailSender javaMailSender;
    public void sendEmail(String to, String subject, String message) {


        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
    }
}
