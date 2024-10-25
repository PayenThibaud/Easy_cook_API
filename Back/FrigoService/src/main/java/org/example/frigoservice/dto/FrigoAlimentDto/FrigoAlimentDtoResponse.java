package org.example.frigoservice.dto.FrigoAlimentDto;

import org.example.frigoservice.dto.FrigoDto.FrigoDtoResponse;
import org.example.frigoservice.entity.Frigo;

public class FrigoAlimentDtoResponse {
    private int idFrigoAliment;
    private FrigoDtoResponse frigo;
    private int idAliment;
    private int quantity;
}
