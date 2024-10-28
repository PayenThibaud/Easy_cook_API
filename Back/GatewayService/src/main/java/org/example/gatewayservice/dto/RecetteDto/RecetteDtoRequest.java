package org.example.gatewayservice.dto.RecetteDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecetteDtoRequest {
    private String nom;
    private String description;
    private int tempsPreparation;
    private int nombreCalories;
    private List<Integer> ingredients;
    private String regime;
    private double cout;
    private Boolean isVisible;
}