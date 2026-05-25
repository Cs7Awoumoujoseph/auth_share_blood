package com.jojo.authentification_blood_share.Repository;

import com.jojo.authentification_blood_share.securiy.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
}

