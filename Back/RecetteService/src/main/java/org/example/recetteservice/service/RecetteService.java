package org.example.recetteservice.service;

import org.example.recetteservice.dto.RecetteDtoReceive;
import org.example.recetteservice.dto.RecetteDtoSend;
import org.example.recetteservice.entity.Recette;
import org.example.recetteservice.repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RecetteService {

    @Autowired
    private RecetteRepository recetteRepository;

    public RecetteDtoSend getById(int id) {
        Recette recette = recetteRepository.findById(id).orElseThrow();
        return mapToDtoSend(recette);
    }

    public List<RecetteDtoSend> getAll() {
        return StreamSupport.stream(recetteRepository.findAll().spliterator(), false)
                .map(this::mapToDtoSend)
                .collect(Collectors.toList());
    }

    public RecetteDtoSend save(RecetteDtoReceive recetteDtoReceive) {
        Recette recette = mapToEntity(recetteDtoReceive);
        recette = recetteRepository.save(recette);
        return mapToDtoSend(recette);
    }

    public void delete(int id) {
        recetteRepository.deleteById(id);
    }

    public RecetteDtoSend update(int id, RecetteDtoReceive recetteDtoReceive) {
        Recette recette = recetteRepository.findById(id).orElseThrow();
        recette.setNom(recetteDtoReceive.getNom());
        recette.setDescription(recetteDtoReceive.getDescription());
        recette.setTempsPreparation(recetteDtoReceive.getTempsPreparation());
        recette.setNombreCalories(recetteDtoReceive.getNombreCalories());
        recette.setCout(recetteDtoReceive.getCout());
        recette.setIsVisible(recetteDtoReceive.getIsVisible());
        recette.setIsFavorite(recetteDtoReceive.getIsFavorite());
        recette = recetteRepository.save(recette);
        return mapToDtoSend(recette);
    }

    private RecetteDtoSend mapToDtoSend(Recette recette) {
        return RecetteDtoSend.builder()
                .id_recette(recette.getId_recette())
                .nom(recette.getNom())
                .description(recette.getDescription())
                .tempsPreparation(recette.getTempsPreparation())
                .nombreCalories(recette.getNombreCalories())
                .cout(recette.getCout())
                .isVisible(recette.getIsVisible())
                .isFavorite(recette.getIsFavorite())
                .build();
    }

    private Recette mapToEntity(RecetteDtoReceive recetteDtoReceive) {
        return Recette.builder()
                .nom(recetteDtoReceive.getNom())
                .description(recetteDtoReceive.getDescription())
                .tempsPreparation(recetteDtoReceive.getTempsPreparation())
                .nombreCalories(recetteDtoReceive.getNombreCalories())
                .cout(recetteDtoReceive.getCout())
                .isVisible(recetteDtoReceive.getIsVisible())
                .isFavorite(recetteDtoReceive.getIsFavorite())
                .build();
    }

}
