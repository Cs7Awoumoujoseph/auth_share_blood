package com.jojo.authentification_blood_share.restControlleur;

import com.jojo.authentification_blood_share.entities.Government;
import com.jojo.authentification_blood_share.register.GovernmentRegister;
import com.jojo.authentification_blood_share.service.GovernmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")

public class GovernmentRestController {

    @Autowired
    private GovernmentService governmentService;

    @PostMapping("/registerGov")
    public Government register(@RequestBody GovernmentRegister request)
    {
        return governmentService.registerGov(request);
    }

}
