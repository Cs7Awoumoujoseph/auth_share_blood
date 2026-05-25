package com.jojo.authentification_blood_share.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "gouvernement")   // ← AJOUTER CECI
@ToString
@SuperBuilder
public class Government extends Users {
    private String nom;
    private String adresse;
    private String telephone;
    private String ville;

    public Government(String username, String email, boolean active, String password, Date date_inscrption, Date date_connexion, List<Roles> roles, String nom,  String adresse, String telephone, String ville) {
        super(null, username, email, active, password, date_inscrption, date_connexion, roles);
        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.ville = ville;
    }

    public String password () {
        return "jojo404";
    }
}