package org.example.ingredientservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ingredientservice.dto.IngredientDto.IngredientDtoReceive;
import org.example.ingredientservice.dto.IngredientDto.IngredientDtoSend;
import org.example.ingredientservice.dto.ProductDto.OpenFoodFactsResponse;
import org.example.ingredientservice.dto.ProductDto.ProductDto;
import org.example.ingredientservice.entity.Ingredient;
import org.example.ingredientservice.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class IngredientService {
    @Autowired
    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    private IngredientDtoSend IngredientToIngredientDtoSend(Ingredient ingredient) {
        return IngredientDtoSend.builder()
                .id_ingredient(ingredient.getId_ingredient())
                .nom(ingredient.getNom())
                .calories(ingredient.getCalories())
                .barcode(ingredient.getBarcode())
                .allergens(ingredient.getAllergens())
                .build();
    }

    private List<IngredientDtoSend> listeIngredientToListeIngredientDtoSend(List<Ingredient> ingredients) {
        return ingredients.stream().map(this::IngredientToIngredientDtoSend).toList();
    }

    public Ingredient findById(int  id) {
        return ingredientRepository.findById(id).orElse(null);
    }

    public List<IngredientDtoSend> getAll() {
        return listeIngredientToListeIngredientDtoSend((List<Ingredient>) ingredientRepository.findAll());
    }

    public IngredientDtoSend getById(int id) {
        return IngredientToIngredientDtoSend(findById(id));
    }

    public IngredientDtoSend save(IngredientDtoReceive ingredientDtoReceive) {

        Ingredient ingredient = Ingredient.builder()
                .nom(ingredientDtoReceive.getNom())
                .calories(ingredientDtoReceive.getCalories())
                .barcode(ingredientDtoReceive.getBarcode())
                .allergens(ingredientDtoReceive.getAllergens())
                .build();

        return IngredientToIngredientDtoSend(ingredientRepository.save(ingredient));
    }

    public IngredientDtoSend update(int id, IngredientDtoReceive ingredientDtoReceive) {
        Ingredient ingredient = findById(id);

        ingredient.setNom(ingredientDtoReceive.getNom());

        return IngredientToIngredientDtoSend(ingredientRepository.save(ingredient));
    }

    public void delete(int id) { ingredientRepository.deleteById(id); }

}
