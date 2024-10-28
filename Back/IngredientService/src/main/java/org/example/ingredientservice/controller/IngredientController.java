package org.example.ingredientservice.controller;

import org.example.ingredientservice.dto.IngredientDto.IngredientDtoReceive;
import org.example.ingredientservice.dto.IngredientDto.IngredientDtoSend;
import org.example.ingredientservice.dto.ProductDto.ProductDto;
import org.example.ingredientservice.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    @Autowired
    private IngredientService ingredientService;

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDtoSend> getById(@PathVariable int id) {
        return ResponseEntity.ok(ingredientService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<IngredientDtoSend>> getAll() {
        return ResponseEntity.ok(ingredientService.getAll());
    }

    @PostMapping
    public ResponseEntity<IngredientDtoSend> create(@RequestBody IngredientDtoReceive ingredientDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ingredientService.save(ingredientDtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        ingredientService.delete(id);
        return ResponseEntity.ok("ingredient supprimer");
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDtoSend> update(@PathVariable int id, @RequestBody IngredientDtoReceive ingredientDtoReceive) {
        return ResponseEntity.ok(ingredientService.update(id, ingredientDtoReceive));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> search(@RequestParam String name) {
        List<ProductDto> products = ingredientService.searchProductsByName(name);
        return ResponseEntity.ok(products);
    }
}