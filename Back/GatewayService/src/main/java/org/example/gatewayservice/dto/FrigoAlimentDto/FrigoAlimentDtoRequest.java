package org.example.gatewayservice.dto.FrigoAlimentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrigoAlimentDtoRequest {
    private int id_aliment;
    private int nombreAliment;
    private int id_frigo;
}
