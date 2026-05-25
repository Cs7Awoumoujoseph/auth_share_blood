package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.entities.Roles;

public interface RoleService {
    void deleteRole(Long id);
    Roles addRole(Roles role);
}
