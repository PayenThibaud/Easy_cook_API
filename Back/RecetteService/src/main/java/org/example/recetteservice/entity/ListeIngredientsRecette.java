package org.example.recetteservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListeIngredientsRecette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_listeingredientrecette;

    @Column(name = "Id_aliment")
    private int id_aliment;

    @Column(name = "Id_recette")
    private int id_recette;

    private double quantite;

    private Boolean unitegramme;

}