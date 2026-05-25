package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.entities.Hopital;
import com.jojo.authentification_blood_share.register.InscriptionReqHopital;

public interface HopitalService {
    Hopital registerHopital(InscriptionReqHopital requete);

}
