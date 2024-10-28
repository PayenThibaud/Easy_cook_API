package org.example.recetteservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class RecetteDtoSend {
    private int id_recette;
    private String nom;
    private String description;
    private int tempsPreparation;
    private int nombreCalories;
    private List<Integer> ingredients;
    private double cout;
    private Boolean isVisible;
    private Boolean isFavorite;
}