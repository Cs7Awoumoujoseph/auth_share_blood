package com.jojo.authentification_blood_share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AuthentificationBloodShareApplication {
//    @Autowired
//    DonneursService donneursService;

    public static void main(String[] args) {
        SpringApplication.run(AuthentificationBloodShareApplication.class, args);
    }
//
//    @PostConstruct
//    void init_donneurs() {
////ajouter les rôles
//        donneursService.addRoles(new Roles(null, "DONNEUR"));
//        donneursService.addRoles(new Roles(null, "HOPITAL"));
//        donneursService.addRoles(new Roles(null, "ADMIN"));
////ajouter les users
//        donneursService.saveDonneurs(
//                Donneurs
//                        .builder()
//                        .date_naissance(null)
//                        .email(null)
//                        .nom("Awoumou")
//                        .prenom("Joseph")
//                        .username("jojo404")
//                        .password("1234")
//                        .roles(null)
//                        .ville(null)
//                        .adresse(null)
//                        .date_inscription(null)
//                        .telephone(null)
//                        .active(true)
//                        .derniere_connexion(null)
//                        .donneur_id(null)
//
//                        .build());
//        donneursService.saveDonneurs(
//                Donneurs
//                        .builder()
//                        .date_naissance(null)
//                        .donneur_id(null)
//                        .email(null)
//                        .nom("Jeremie")
//                        .prenom("Joseph")
//                        .username("jeremiu45")
//                        .password("1234")
//                        .roles(null)
//                        .ville(null)
//                        .adresse(null)
//                        .date_inscription(null)
//                        .telephone(null)
//                        .active(true)
//                        .derniere_connexion(null)
//
//                        .build());
//        //ajouter les rôles aux users
//        donneursService.addRoleToDonneurs("jojo404","DONNEUR");
//        donneursService.addRoleToDonneurs("jeremiu45","DONNEUR");




//
//    }
//    @Bean
//    BCryptPasswordEncoder getBCE() {
//        return new BCryptPasswordEncoder();
//    }

}