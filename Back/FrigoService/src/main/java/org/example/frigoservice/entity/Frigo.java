package org.example.frigoservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Frigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_frigo;
    private int id_utilisateur;
    private String nom;
}

