package org.example.gatewayservice.dto.FrigoDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrigoDtoResponse {
    private int id_frigo;
    private int id_utilisateur;
    private String nom;
}
