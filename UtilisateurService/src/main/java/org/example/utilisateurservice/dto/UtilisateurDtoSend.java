package org.example.utilisateurservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UtilisateurDtoSend {
    private int id_utilisateur;
    private String nom;
    private String email;
}