package com.jojo.authentification_blood_share.register;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InscriptionReqDonneurs {
    public String username;
    public String password;
    public String email;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String ville;

}
