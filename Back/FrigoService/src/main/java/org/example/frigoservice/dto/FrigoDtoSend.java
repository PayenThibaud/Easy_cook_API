package org.example.frigoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FrigoDtoSend {
    private int id_frigo;
    private int id_utilisateur;
}
