package org.example.utilisateurservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.utilisateurservice.utils.enums.Role;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UtilisateurDtoSend {
    private int id_utilisateur;
    private String nom;
    private String email;
    private String password;
    private int phone;
    private Role role = Role.USER;
}