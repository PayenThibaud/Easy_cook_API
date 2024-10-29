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
    private ListeIngredientsRecetteRepository listeIngredientsRecetteRepository; // Modification ici

    public ListeIngredientsRecetteDtoSend getById(int id) {
        ListeIngredientsRecette listeIngredientsRecette = listeIngredientsRecetteRepository.findById(id).orElseThrow();
        return mapToDtoSend(listeIngredientsRecette);
    }

    public List<ListeIngredientsRecetteDtoSend> getAll() {
        return StreamSupport.stream(listeIngredientsRecetteRepository.findAll().spliterator(), false)
                .map(this::mapToDtoSend)
                .collect(Collectors.toList());
    }

    public ListeIngredientsRecetteDtoSend save(ListeIngredientsRecetteDtoReceive dtoReceive) {
        ListeIngredientsRecette listeIngredientsRecette = mapToEntity(dtoReceive);
        listeIngredientsRecette = listeIngredientsRecetteRepository.save(listeIngredientsRecette);
        return mapToDtoSend(listeIngredientsRecette);
    }

    public void delete(int id) {
        listeIngredientsRecetteRepository.deleteById(id);
    }

      public ListeIngredientsRecetteDtoSend update(int id, ListeIngredientsRecetteDtoReceive dtoReceive) {
        ListeIngredientsRecette listeIngredientsRecette = listeIngredientsRecetteRepository.findById(id).get();
        listeIngredientsRecette.setIngredientId(dtoReceive.getIngredientIds());
        listeIngredientsRecette.setRegimeId(dtoReceive.getRegimeIds());
        listeIngredientsRecette.setQuantite(dtoReceive.getQuantite());

        listeIngredientsRecette = listeIngredientsRecetteRepository.save(listeIngredientsRecette);
        return mapToDtoSend(listeIngredientsRecette);
    }

    private ListeIngredientsRecetteDtoSend mapToDtoSend(ListeIngredientsRecette listeIngredientsRecette) {
        return ListeIngredientsRecetteDtoSend.builder()
                .ingredientIds(listeIngredientsRecette.getIngredientId())
                .regimeIds(listeIngredientsRecette.getRegimeId())
                .quantite(listeIngredientsRecette.getQuantite())
                .build();
    }

    private ListeIngredientsRecette mapToEntity(ListeIngredientsRecetteDtoReceive dtoReceive) {
        return ListeIngredientsRecette.builder()
                .ingredientId(dtoReceive.getIngredientIds())
                .regimeId(dtoReceive.getRegimeIds())
                .quantite(dtoReceive.getQuantite())
                .build();
    }
}

