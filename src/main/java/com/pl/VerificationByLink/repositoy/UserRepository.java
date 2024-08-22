package com.pl.VerificationByLink.repositoy;

import com.pl.VerificationByLink.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByVerificationToken(String token);
}
