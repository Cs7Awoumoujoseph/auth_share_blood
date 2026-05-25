package com.jojo.authentification_blood_share.Repository;

import com.jojo.authentification_blood_share.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles, Long> {
    Roles findByRoles(String name);
}
