package org.example.frigoingredientservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class FrigoAlimentDtoReceive
{
    private int id_aliment;
    private int nombreAliment;
    private int id_frigo;
}
