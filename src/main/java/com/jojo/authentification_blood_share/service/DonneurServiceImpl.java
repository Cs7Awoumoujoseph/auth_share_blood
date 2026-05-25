package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.Repository.DonneursRepository;
import com.jojo.authentification_blood_share.Repository.RolesRepository;
import com.jojo.authentification_blood_share.Repository.VerificationTokenRepository;
import com.jojo.authentification_blood_share.entities.Donneurs;
import com.jojo.authentification_blood_share.entities.Roles;
import com.jojo.authentification_blood_share.exceptrions.EmailAlreadyExistsException;
import com.jojo.authentification_blood_share.register.InscriptionReqDonneurs;
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
public class DonneurServiceImpl implements DonneursService {
    @Autowired
    DonneursRepository donneursRepository ;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserService userService;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;



    @Override
    public Donneurs registerDonneurs(InscriptionReqDonneurs requete){
        Optional<Donneurs> optionalDonneur = donneursRepository.findByEmail(requete.getEmail());
        if(optionalDonneur.isPresent())
            throw new EmailAlreadyExistsException("email déjà existant!");
        Donneurs nouveauDonneurs = new Donneurs();
        nouveauDonneurs.setUsername(requete.getUsername());
        nouveauDonneurs.setEmail(requete.getEmail());

        nouveauDonneurs.setPassword(bCryptPasswordEncoder.encode(requete.getPassword()));
        nouveauDonneurs.setNom(requete.getNom());
        nouveauDonneurs.setPrenom(requete.getPrenom());
        nouveauDonneurs.setAdresse(requete.getAdresse());
        nouveauDonneurs.setTelephone(requete.getTelephone());
        nouveauDonneurs.setVille(requete.getVille());
        nouveauDonneurs.setActive(false);

        donneursRepository.save(nouveauDonneurs);
        //ajouter à newUser le role par défaut USER
        Roles r = rolesRepository.findByRoles("DONNEUR");
        List<Roles> roles = new ArrayList<>();
        roles.add(r);
        nouveauDonneurs.setRoles(roles);

        donneursRepository.save(nouveauDonneurs);
        //génére le code secret
        String code = userService.generateCode();

        VerificationToken token = new VerificationToken(code, nouveauDonneurs);
        verificationTokenRepository.save(token);

        //envoyer par email pour valider l'email de l'utilisateur
        userService.sendEmailUser(nouveauDonneurs,token.getToken());

        return nouveauDonneurs;

    }
}
