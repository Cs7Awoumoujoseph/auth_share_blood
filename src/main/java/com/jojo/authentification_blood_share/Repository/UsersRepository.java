package com.jojo.authentification_blood_share.Repository;

import com.jojo.authentification_blood_share.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Optional<Users> findByEmail(String emnail);

}
