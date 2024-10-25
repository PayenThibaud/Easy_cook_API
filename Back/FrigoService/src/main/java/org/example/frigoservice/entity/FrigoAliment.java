package org.example.frigoservice.entity;

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
public class FrigoAliment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_frigoAliment;

    @ManyToOne
    @JoinColumn(name = "frigo_id")
    private Frigo frigo;

    private int id_aliment;
    private int quantity;
}


