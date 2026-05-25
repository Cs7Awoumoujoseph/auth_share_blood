package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.entities.Donneurs;
import com.jojo.authentification_blood_share.register.InscriptionReqDonneurs;
import org.springframework.stereotype.Service;

public interface DonneursService {
    Donneurs registerDonneurs(InscriptionReqDonneurs requete);

}
