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
}