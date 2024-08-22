package com.pl.VerificationByLink.service;

import com.pl.VerificationByLink.model.User;

public interface UserService {

    void registerUser(User user);
    boolean verify(String token);
}
