package com.jojo.authentification_blood_share.securiy;

import com.jojo.authentification_blood_share.entities.Donneurs;
import com.jojo.authentification_blood_share.entities.Users;
import com.jojo.authentification_blood_share.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    @Autowired
    UserService userService;

    //on redefini la methode de linterface sprigboot , ce nest pas ma methosde que jai cree
    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        Users users = userService.findByUsername(username);
        if (users==null)
            throw new UsernameNotFoundException("Utilisateur introuvable !");
        List<GrantedAuthority> auths = new ArrayList<>();
        users.getRoles().forEach(role -> {
            GrantedAuthority auhority = new
                    SimpleGrantedAuthority(role.getRoles());
            auths.add(auhority);
        });
        //on retourne aussi le user actif ou pas pour verifier des choses oooh
        return new org.springframework.security.core.
                userdetails.User(users.getUsername(),users.getPassword(),users.isActive(),true,true
                ,true,auths);

    }
}

