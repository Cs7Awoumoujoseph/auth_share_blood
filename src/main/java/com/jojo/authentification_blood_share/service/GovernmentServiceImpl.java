package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.Repository.DonneursRepository;
import com.jojo.authentification_blood_share.Repository.GovernmentRepository;
import com.jojo.authentification_blood_share.Repository.RolesRepository;
import com.jojo.authentification_blood_share.Repository.VerificationTokenRepository;
import com.jojo.authentification_blood_share.config_env.AppConfig;
import com.jojo.authentification_blood_share.entities.Donneurs;
import com.jojo.authentification_blood_share.entities.Government;
import com.jojo.authentification_blood_share.entities.Hopital;
import com.jojo.authentification_blood_share.entities.Roles;
import com.jojo.authentification_blood_share.exceptrions.EmailAlreadyExistsException;
import com.jojo.authentification_blood_share.register.GovernmentRegister;
import com.jojo.authentification_blood_share.securiy.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class GovernmentServiceImpl implements GovernmentService {
    @Autowired
    GovernmentRepository governmentRepository ;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;


    private final AppConfig appConfig;//pour le mdp secret

    public GovernmentServiceImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Override
    public Government registerGov(GovernmentRegister requete) {
        Optional<Government> optionalGovernment = governmentRepository.findByEmail(requete.getEmail());
        if(optionalGovernment.isPresent())
            throw new EmailAlreadyExistsException("email déjà existant!");
        if(!Objects.equals(requete.password, appConfig.getSecretPassword())) throw new EmailAlreadyExistsException("Mouf Mot de passe Gouvernement errone");
        Government nouveauGouvernement = new Government();
        nouveauGouvernement.setUsername(requete.getUsername());
        nouveauGouvernement.setEmail(requete.getEmail());

        nouveauGouvernement.setPassword(bCryptPasswordEncoder.encode(requete.getPassword()));
        nouveauGouvernement.setNom(requete.getNom());
        nouveauGouvernement.setAdresse(requete.getAdresse());
        nouveauGouvernement.setTelephone(requete.getTelephone());
        nouveauGouvernement.setVille(requete.getVille());
        nouveauGouvernement.setActive(false);

        governmentRepository.save(nouveauGouvernement);
        //ajouter à newUser le role par défaut USER
        Roles r = rolesRepository.findByRoles("MINSANTE");
        List<Roles> roles = new ArrayList<>();
        roles.add(r);
        nouveauGouvernement.setRoles(roles);

        governmentRepository.save(nouveauGouvernement);
        //génére le code secret
        String code = userService.generateCode();

        VerificationToken token = new VerificationToken(code, nouveauGouvernement);
        verificationTokenRepository.save(token);

        //envoyer par email pour valider l'email de l'utilisateur
        userService.sendEmailUser(nouveauGouvernement,token.getToken());

        return nouveauGouvernement;    }
}
