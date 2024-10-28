package org.example.frigoingredientservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FrigoAliment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_frigoAliment;
    private int id_aliment;
    private int nombreAliment;
    private int id_frigo;
}
