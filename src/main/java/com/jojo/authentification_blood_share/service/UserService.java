package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.entities.Roles;
import com.jojo.authentification_blood_share.entities.Users;

import java.util.List;


public interface UserService {
    Users saveUser(Users users);
    Users findByUsername(String username);
    Roles addRoles(Roles roles );
    Users addRoleToUser(String username, String rolename);
    List<Users> findAllUser();
    public Users validateToken(String code);
    public void sendEmailUser(Users u, String code) ;
    public String generateCode();

}
