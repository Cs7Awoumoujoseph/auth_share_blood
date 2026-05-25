package com.jojo.authentification_blood_share.restControlleur;

import com.jojo.authentification_blood_share.entities.Donneurs;
import com.jojo.authentification_blood_share.register.InscriptionReqDonneurs;
import com.jojo.authentification_blood_share.service.DonneursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class DonneursRestControlleur {

    @Autowired
    private DonneursService donneursService;

    @PostMapping("/register")
    public Donneurs register(@RequestBody InscriptionReqDonneurs request)
    {
        return donneursService.registerDonneurs(request);
    }




}
