package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.entities.Roles;

import java.util.List;

public interface RoleService {
    void deleteRole(Long id);
    Roles addRole(Roles role);
    List<Roles> getAllRoles();
}
