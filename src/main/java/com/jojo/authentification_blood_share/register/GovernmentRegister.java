package com.jojo.authentification_blood_share.register;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GovernmentRegister {
    public String username;
    public String password;
    public String email;
    private String nom;
    private String adresse;
    private String telephone;
    private String ville;
}
