package org.example.frigoservice.service;

import org.example.frigoservice.dto.FrigoDto.FrigoDtoRequest;
import org.example.frigoservice.dto.FrigoDto.FrigoDtoResponse;
import org.example.frigoservice.entity.Frigo;

import java.util.List;

public interface FrigoService {
    FrigoDtoResponse createFrigo(FrigoDtoRequest frigoDtoRequest) ;
    boolean deleteFrigo(int id) ;
    FrigoDtoResponse getFrigoById(int id) ;
    List<FrigoDtoResponse> getAllFrigo() ;
    Frigo getFrigoByUtilisateurId(int id);
}
