package org.example.recetteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ListeIngredientsRecetteDtoReceive {
    private int id_aliment;
    private int id_recette;
    private double quantite;
    private Boolean unitegramme;
}
