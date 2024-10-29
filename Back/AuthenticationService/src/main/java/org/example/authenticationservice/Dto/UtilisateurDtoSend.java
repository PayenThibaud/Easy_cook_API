package org.example.authenticationservice.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.authenticationservice.utils.enums.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDtoSend {
    private int id_utilisateur;
    private String pseudo;
    private String email;
    private String password;
    private Role role = Role.USER;
}