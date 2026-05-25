package com.jojo.authentification_blood_share.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@NoArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "donneurs")   // ← AJOUTER CECI
@ToString
@SuperBuilder
public class Donneurs extends Users{
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    private String ville;

    public Donneurs(String username, String email, boolean active, String password, Date date_inscrption, Date date_connexion,List<Roles> roles, String nom, String prenom, String adresse, String telephone, String ville) {
         super(null,username,email,active,password,date_inscrption,date_connexion,roles);
         this.nom=nom;
         this.prenom=prenom;
         this.adresse=adresse;
         this.telephone=telephone;
         this.ville=ville;
    }
    public void setDate_inscrption(Date date_inscrption){

    }


}
