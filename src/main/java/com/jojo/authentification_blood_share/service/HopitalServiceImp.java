package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.Repository.HopitalRepository;
import com.jojo.authentification_blood_share.Repository.RolesRepository;
import com.jojo.authentification_blood_share.Repository.VerificationTokenRepository;
import com.jojo.authentification_blood_share.entities.Hopital;
import com.jojo.authentification_blood_share.entities.Roles;
import com.jojo.authentification_blood_share.exceptrions.EmailAlreadyExistsException;
import com.jojo.authentification_blood_share.register.InscriptionReqHopital;
import com.jojo.authentification_blood_share.securiy.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Transactional
@Service
public class HopitalServiceImp implements HopitalService{
    @Autowired
    HopitalRepository hopitalRepository ;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Override
    public Hopital registerHopital(InscriptionReqHopital requete){
        Optional<Hopital> optionalHopital = hopitalRepository.findByEmail(requete.getEmail());
        if(optionalHopital.isPresent())
            throw new EmailAlreadyExistsException("email déjà existant!");
        Hopital nouveauHopital = new Hopital();
        nouveauHopital.setUsername(requete.getUsername());
        nouveauHopital.setEmail(requete.getEmail());

        nouveauHopital.setPassword(bCryptPasswordEncoder.encode(requete.getPassword()));
        nouveauHopital.setHopital_name(requete.getHopital_name());
        nouveauHopital.setAdresse(requete.getAdresse());
        nouveauHopital.setHopital_type(requete.getHopital_type());
        nouveauHopital.setTelephone(requete.getTelephone());
        nouveauHopital.setVille(requete.getVille());
        nouveauHopital.setActive(false);

        hopitalRepository.save(nouveauHopital);
        //ajouter à newUser le role par défaut USER
        Roles r = rolesRepository.findByRoles("HOPITAL");
        List<Roles> roles = new ArrayList<>();
        roles.add(r);
        nouveauHopital.setRoles(roles);

        hopitalRepository.save(nouveauHopital);
        //génére le code secret
        String code = userService.generateCode();

        VerificationToken token = new VerificationToken(code, nouveauHopital);
        verificationTokenRepository.save(token);

        //envoyer par email pour valider l'email de l'utilisateur
        userService.sendEmailUser(nouveauHopital,token.getToken());

        return nouveauHopital;

    }

}
