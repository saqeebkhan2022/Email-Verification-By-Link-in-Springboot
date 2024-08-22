package com.pl.VerificationByLink.service.IMPL;

import com.pl.VerificationByLink.model.User;
import com.pl.VerificationByLink.repositoy.UserRepository;
import com.pl.VerificationByLink.service.EmailService;
import com.pl.VerificationByLink.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public void registerUser(User user) {
    user.setVerified(false);
    user.setVerificationToken(UUID.randomUUID().toString());
    userRepository.save(user);
    emailService.sendVerificationEmail(user.getEmail(), user.getVerificationToken());
    }

    @Override
    public boolean verify(String token) {
      User user = userRepository.findByVerificationToken(token);
      if (user != null){
          user.setVerified(true);
          user.setVerificationToken(null);
          userRepository.save(user);
          return true;
      }
      return false;
    }
}
