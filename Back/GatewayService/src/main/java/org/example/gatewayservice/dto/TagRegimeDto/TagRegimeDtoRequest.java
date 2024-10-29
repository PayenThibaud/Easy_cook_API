package org.example.gatewayservice.dto.TagRegimeDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagRegimeDtoRequest {
    private int recetteId;
    private int contrainteAlimentaireId;
}
