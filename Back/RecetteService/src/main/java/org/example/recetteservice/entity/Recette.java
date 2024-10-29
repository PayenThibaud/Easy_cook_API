package org.example.recetteservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Recette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_recette;
    private String nom;
    private String description;
    private int tempsPreparation;
    private int nombreCalories;
    private double cout;
    private Boolean isVisible;
    private Boolean isFavorite;

}