package com.jojo.authentification_blood_share.Repository;

import com.jojo.authentification_blood_share.entities.Donneurs;
import com.jojo.authentification_blood_share.entities.Hopital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HopitalRepository extends JpaRepository<Hopital, Integer> {
    Optional<Hopital> findByEmail(String email);

}
