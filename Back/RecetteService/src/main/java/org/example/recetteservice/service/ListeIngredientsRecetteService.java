package org.example.recetteservice.service;

import org.example.recetteservice.dto.ListeIngredientsRecetteDtoReceive;
import org.example.recetteservice.dto.ListeIngredientsRecetteDtoSend;
import org.example.recetteservice.entity.ListeIngredientsRecette;
import org.example.recetteservice.repository.ListeIngredientsRecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ListeIngredientsRecetteService {

    @Autowired
    private ListeIngredientsRecetteRepository listeIngredientsRecetteRepository;

    public ListeIngredientsRecetteDtoSend getById(int id) {
        ListeIngredientsRecette ingredient = listeIngredientsRecetteRepository.findById(id).orElseThrow();
        return mapToDtoSend(ingredient);
    }

    public List<ListeIngredientsRecetteDtoSend> getAll() {
        return StreamSupport.stream(listeIngredientsRecetteRepository.findAll().spliterator(), false)
                .map(this::mapToDtoSend)
                .collect(Collectors.toList());
    }

    public ListeIngredientsRecetteDtoSend save(ListeIngredientsRecetteDtoReceive dtoReceive) {
        ListeIngredientsRecette ingredient = mapToEntity(dtoReceive);
        ingredient = listeIngredientsRecetteRepository.save(ingredient);
        return mapToDtoSend(ingredient);
    }

    public void delete(int id) {
        listeIngredientsRecetteRepository.deleteById(id);
    }

    public ListeIngredientsRecetteDtoSend update(int id, ListeIngredientsRecetteDtoReceive dtoReceive) {
        ListeIngredientsRecette ingredient = listeIngredientsRecetteRepository.findById(id).orElseThrow();
        ingredient.setId_aliment(dtoReceive.getId_aliment());
        ingredient.setId_recette(dtoReceive.getId_recette());
        ingredient.setQuantite(dtoReceive.getQuantite());
        ingredient.setUnitegramme(dtoReceive.getUnitegramme());
        ingredient = listeIngredientsRecetteRepository.save(ingredient);
        return mapToDtoSend(ingredient);
    }

    private ListeIngredientsRecetteDtoSend mapToDtoSend(ListeIngredientsRecette ingredient) {
        return ListeIngredientsRecetteDtoSend.builder()
                .id_listeingredientrecette(ingredient.getId_listeingredientrecette())
                .id_aliment(ingredient.getId_aliment())
                .id_recette(ingredient.getId_recette())
                .quantite(ingredient.getQuantite())
                .unitegramme(ingredient.getUnitegramme())
                .build();
    }

    private ListeIngredientsRecette mapToEntity(ListeIngredientsRecetteDtoReceive dtoReceive) {
        return ListeIngredientsRecette.builder()
                .id_aliment(dtoReceive.getId_aliment())
                .id_recette(dtoReceive.getId_recette())
                .quantite(dtoReceive.getQuantite())
                .unitegramme(dtoReceive.getUnitegramme())
                .build();
    }
}
