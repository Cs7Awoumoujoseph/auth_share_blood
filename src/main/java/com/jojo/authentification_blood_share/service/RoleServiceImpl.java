package com.jojo.authentification_blood_share.service;


import com.jojo.authentification_blood_share.Repository.RolesRepository;
import com.jojo.authentification_blood_share.entities.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RolesRepository rolesRepository;

    @Override
    public void deleteRole(Long id) {
      rolesRepository.deleteById( id);
    }

    @Override
    public Roles addRole(Roles role) {
        return rolesRepository.save(role);
    }
}
