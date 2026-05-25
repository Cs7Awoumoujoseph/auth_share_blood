package com.jojo.authentification_blood_share.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name = "hopital")    // ← AJOUTER CECI

@SuperBuilder

public class Hopital extends Users {

    private String hopital_name;
    private String hopital_type;
    private String adresse;
    private String telephone;
    private String ville;

    public Hopital(String username, String email, boolean active, String password, Date date_inscrption, Date date_connexion, List<Roles> roles, String hopitalbnaom, String hopitaltype, String adresse, String telephone, String ville) {
        super(null,username,email,active,password,date_inscrption,date_connexion,roles);
        this.hopital_name=hopitalbnaom;
        this.hopital_type=hopitaltype;
        this.adresse=adresse;
        this.telephone=telephone;
        this.ville=ville;
    }


}
