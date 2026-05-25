package com.jojo.authentification_blood_share.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    @Column(unique = true)
    private String email;
    private boolean active;

    @Column(unique = true)
    private String password;
    private Date date_inscription ;
    private Date derniere_connexion;

    @PrePersist                          // ← appelé automatiquement avant le 1er save()
    protected void onCreate() {
        this.date_inscription = new Date();
    }

    @PreUpdate //appele a chaque save de User
    protected void onUpdate() {
        this.date_inscription = new Date();
    }
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_role",joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> roles;

}
