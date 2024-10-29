package org.example.recetteservice.controller;

import org.example.recetteservice.dto.ListeIngredientsRecetteDtoReceive;
import org.example.recetteservice.dto.ListeIngredientsRecetteDtoSend;
import org.example.recetteservice.service.ListeIngredientsRecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listeIngredients")
public class ListeIngredientsRecetteController {

    @Autowired
    private ListeIngredientsRecetteService listeIngredientsRecetteService;

    @GetMapping("/{id}")
    public ResponseEntity<ListeIngredientsRecetteDtoSend> getById(@PathVariable int id) {
        return ResponseEntity.ok(listeIngredientsRecetteService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ListeIngredientsRecetteDtoSend>> getAll() {
        return ResponseEntity.ok(listeIngredientsRecetteService.getAll());
    }

    @PostMapping
    public ResponseEntity<ListeIngredientsRecetteDtoSend> create(@RequestBody ListeIngredientsRecetteDtoReceive dtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(listeIngredientsRecetteService.save(dtoReceive));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListeIngredientsRecetteDtoSend> update(@PathVariable int id, @RequestBody ListeIngredientsRecetteDtoReceive dtoReceive) {
        return ResponseEntity.ok(listeIngredientsRecetteService.update(id, dtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        listeIngredientsRecetteService.delete(id);
        return ResponseEntity.ok("Ingrédient supprimé");
    }
}
