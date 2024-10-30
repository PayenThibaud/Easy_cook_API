package org.example.gatewayservice.dto.ListeIngredientsRecetteDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListeIngredientsRecetteDtoResponse {
    private int id_listeingredientrecette;
    private int id_aliment;
    private int id_recette;
    private double quantite;
    private Boolean unitegramme;
}

