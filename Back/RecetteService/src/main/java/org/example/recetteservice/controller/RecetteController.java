package org.example.recetteservice.controller;

import org.example.recetteservice.dto.RecetteDtoReceive;
import org.example.recetteservice.dto.RecetteDtoSend;
import org.example.recetteservice.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recettes")
public class RecetteController {
    @Autowired
    private RecetteService recetteService;

    @PostMapping
    public ResponseEntity<Recette> addRecipe(@RequestBody Recette recette) {
        Recette newRecipe = recetteService.addRecipe(recette);
        return ResponseEntity.status(HttpStatus.CREATED).body(newRecipe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recette> updateRecipe(@PathVariable Long id, @RequestBody Recette recetteDetails) {
        Recette updatedRecipe = recetteService.updateRecipe(id, recetteDetails);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recetteService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/all")
    public List<Recette> getAllRecipes() {
        return recetteService.getAllRecipes();
    }

    @GetMapping("/search")
    public List<Recette> searchRecipes(@RequestParam String keyword) {
        return recetteService.searchRecipes(keyword);
    }

    @PostMapping("/by-ingredients")
    public ResponseEntity<List<Recette>> getRecipesByIngredients(@RequestBody List<Ingredient> ingredients) {
        List<Recette> recettes = recetteService.getRecipesByIngredients(ingredients);
        return ResponseEntity.ok(recettes);
    }
}