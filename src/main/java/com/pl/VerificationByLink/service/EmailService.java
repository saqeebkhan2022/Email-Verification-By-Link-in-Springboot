package com.pl.VerificationByLink.service;

public interface EmailService {

    void sendVerificationEmail(String toEmail, String token);
}
