package org.example.contraintealimentaireservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagRegimeDTOSend {
    private int id;
    private int recetteId;
    private int contrainteAlimentaireId;
}
