package org.example.frigoservice.dto.FrigoAlimentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.frigoservice.dto.FrigoDto.FrigoDtoResponse;
import org.example.frigoservice.entity.Frigo;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FrigoAlimentDtoRequest {
    private int idFrigo;
    private int idAliment;
    private int quantity;
}
