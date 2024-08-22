package com.pl.VerificationByLink.service.IMPL;

import com.pl.VerificationByLink.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl  implements EmailService {

    private final JavaMailSender javaMailSender;
    @Value("$spring.mail.username")
    private String fromEmail;

    @Override
    public void sendVerificationEmail(String toEmail, String token) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        try {
            String htmlMsg = "<html><body>"
                    + "<h2>Verify your email address</h2>"
                    + "<p>Click the button below to verify your email address:</p>"
                    + "<a href=\"http://localhost:8080/verify?token=" + token + "\" style=\""
                    + "background-color: #008CBA; border: none; color: white; padding: 15px 32px; "
                    + "text-align: center; text-decoration: none; display: inline-block; font-size: 16px; "
                    + "margin: 4px 2px; cursor: pointer; border-radius: 10px;\">Verify Email</a>"
                    + "</body></html>";
            helper.setText(htmlMsg, true);
            helper.setTo(toEmail);
            helper.setSubject("Verify your email address");
            helper.setFrom(fromEmail);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            // Handle exception
        }
    }
}
