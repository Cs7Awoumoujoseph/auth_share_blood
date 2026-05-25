package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.Repository.RolesRepository;
import com.jojo.authentification_blood_share.Repository.UsersRepository;
import com.jojo.authentification_blood_share.Repository.VerificationTokenRepository;
import com.jojo.authentification_blood_share.entities.Roles;
import com.jojo.authentification_blood_share.entities.Users;
import com.jojo.authentification_blood_share.exceptrions.ExpiredTokenException;
import com.jojo.authentification_blood_share.exceptrions.InvalidTokenException;
import com.jojo.authentification_blood_share.securiy.VerificationToken;
import com.jojo.authentification_blood_share.util.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class UsersServiceImpl implements UserService{
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmailSender emailSender ;



    @Override
    public Users saveUser(  Users users) {
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        return usersRepository.save(users) ;
    }
    @Override
    public Users findByUsername(String name) {
        return usersRepository.findByUsername(name);
    }

    @Override
    public Roles addRoles(Roles roles) {
        return rolesRepository.save(roles);
    }



    @Override
    public Users addRoleToUser(String username, String rolename) {
        Users dn = usersRepository.findByUsername(username);
        Roles rol = rolesRepository.findByRoles(rolename) ;

        dn.getRoles().add(rol);
        return dn;
    }

    @Override
    public List<Users> findAllUser() {
        return usersRepository.findAll();
    }


    @Override
    public String generateCode() {
        Random random = new Random();
        Integer code = 100000 + random.nextInt(900000);

        return code.toString();
    }

    @Override
    public void sendEmailUser(Users u, String code) {
        String emailBody ="Bonjour "+ "<h1>"+u.getUsername() +"</h1>" +
                " Votre code de validation est "+"<h1>"+code+"</h1>";
        emailSender.sendEmail(u.getEmail(), emailBody);
    }

    @Override
    public Users validateToken(String code) {
        VerificationToken token = verificationTokenRepository.findByToken(code) ;
        if(token == null){
            throw new InvalidTokenException("Invalid Token");
        }

        Users users = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
            verificationTokenRepository.delete(token);
            throw new ExpiredTokenException("expired Token");
        }
        users.setActive(true);
        usersRepository.save(users);
        return users;
    }



}
