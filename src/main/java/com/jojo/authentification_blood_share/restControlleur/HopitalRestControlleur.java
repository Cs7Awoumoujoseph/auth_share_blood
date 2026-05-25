package com.jojo.authentification_blood_share.restControlleur;


import com.jojo.authentification_blood_share.entities.Hopital;
import com.jojo.authentification_blood_share.register.InscriptionReqHopital;
import com.jojo.authentification_blood_share.service.HopitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class HopitalRestControlleur {

    @Autowired
    private HopitalService hopitalService;

    @PostMapping("/registerHospital")
    public Hopital register(@RequestBody InscriptionReqHopital request)
    {
        return hopitalService.registerHopital(request);
    }
}
