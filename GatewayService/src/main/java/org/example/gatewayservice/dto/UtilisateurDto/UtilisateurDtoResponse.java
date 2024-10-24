package org.example.gatewayservice.dto.UtilisateurDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDtoResponse {
    private int id_utilisateur;
    private String nom;
    private String email;
}
