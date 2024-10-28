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
@RequestMapping("/recettes")
public class RecetteController {
    @Autowired
    private RecetteService recetteService;

    @GetMapping("/{id}")
    public ResponseEntity<RecetteDtoSend> getById(@PathVariable int id) {
        return ResponseEntity.ok(recetteService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RecetteDtoSend>> getAll() {
        return ResponseEntity.ok(recetteService.getAll());
    }

    @GetMapping
    public ResponseEntity<List<RecetteDtoSend>> getRecettesDuFrigo(@RequestParam int utilisateurId) {
        List<RecetteDtoSend> recettes = recetteService.getRecettesDuFrigo(utilisateurId);
        return ResponseEntity.ok(recettes);
    }

    @PostMapping
    public ResponseEntity<RecetteDtoSend> create(@RequestBody RecetteDtoReceive recetteDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recetteService.save(recetteDtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        recetteService.delete(id);
        return ResponseEntity.ok("Recette supprimée");
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetteDtoSend> update(@PathVariable int id, @RequestBody RecetteDtoReceive recetteDtoReceive) {
        return ResponseEntity.ok(recetteService.update(id, recetteDtoReceive));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<RecetteDtoSend>> getRecettesByRegime(@RequestParam String regime) {
        Regime selectedRegime = Regime.valueOf(regime.toUpperCase());
        List<Recette> recettes = recetteService.getAll();
        List<Recette> filteredRecettes = recetteService.filterRecettesByRegime(recettes, selectedRegime);

        List<RecetteDtoSend> dtoList = filteredRecettes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtoList);
    }
}