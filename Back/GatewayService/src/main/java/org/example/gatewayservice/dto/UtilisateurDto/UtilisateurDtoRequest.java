package org.example.gatewayservice.dto.UtilisateurDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDtoRequest {
    private String nom;
    private String email;
}
