package org.example.recetteservice.service;

import org.example.recetteservice.dto.RecetteDtoReceive;
import org.example.recetteservice.dto.RecetteDtoSend;
import org.example.recetteservice.entity.Recette;
import org.example.recetteservice.repository.RecetteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecetteService {
    @Autowired
    private RecetteRepository recetteRepository;

    public Recette addRecipe(Recette recette) {
        return recetteRepository.save(recette);
    }

    public Recette updateRecipe(Long id, Recette recetteDetails) {
        Recette recette = recetteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recette not found for this id :: " + id));
        recette.setNom(recetteDetails.getNom());
        recette.setDescription(recetteDetails.getDescription());
        recette.setCalories(recetteDetails.getCalories());
        recette.setIngredients(recetteDetails.getIngredients());
        recette.setIsVisible(recetteDetails.getIsVisible());
        return recetteRepository.save(recette);
    }

    public void deleteRecipe(Long id) {
        Recette recette = recetteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recette not found for this id :: " + id));
        recetteRepository.delete(recette);
    }

    public List<Recette> getAllRecipes() {
        return recetteRepository.findAll(); // Récupérer toutes les recettes
    }

    public List<Recette> searchRecipes(String keyword) {
        return recetteRepository.findByNomContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public List<Recette> getRecipesByIngredients(List<Ingredient> ingredients) {
        return recetteRepository.findByIngredientsIn(ingredients);
    }


}