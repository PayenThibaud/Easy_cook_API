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
    private int id;

    @Column(name = "Id_aliment")
    private int ingredientId;

    @Column(name = "Id_contrainte_alimentaire")
    private int regimeId;

    private double quantite;

}