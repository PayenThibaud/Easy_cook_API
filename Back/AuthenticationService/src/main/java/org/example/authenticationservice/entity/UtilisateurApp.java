package org.example.authenticationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authenticationservice.utils.enums.Role;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UtilisateurApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_utilisateurApp;

    private String nom;
    private String email;
    private String password;
    private int phone;
    private Role role;

    public UtilisateurApp(String email, String lastname, int phone, String password,int role) {
        this.nom = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role == 0 ? Role.USER : Role.ADMIN;
    }
}
