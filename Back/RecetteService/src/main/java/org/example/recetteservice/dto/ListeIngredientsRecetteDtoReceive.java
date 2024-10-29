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
    private int ingredientIds;
    private int regimeIds;
    private double quantite;
}
