package org.example.frigoservice.dto.AlimentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlimentDtoResponse {
    private int id_aliment;
    private String nom;
}
