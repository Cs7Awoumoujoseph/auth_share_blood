package com.jojo.authentification_blood_share.service;

import com.jojo.authentification_blood_share.entities.Government;
import com.jojo.authentification_blood_share.register.GovernmentRegister;

public interface GovernmentService {
    Government registerGov(GovernmentRegister requete);

}
