package com.jojo.authentification_blood_share.Repository;

import com.jojo.authentification_blood_share.entities.Government;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GovernmentRepository extends JpaRepository<Government, Integer> {
    Optional<Government> findByEmail(String email);

}
