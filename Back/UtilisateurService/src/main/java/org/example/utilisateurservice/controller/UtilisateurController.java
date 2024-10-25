package org.example.utilisateurservice.controller;

import org.example.utilisateurservice.dto.UtilisateurDtoReceive;
import org.example.utilisateurservice.dto.UtilisateurDtoSend;
import org.example.utilisateurservice.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {
    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoSend> getById(@PathVariable int id) {
        return ResponseEntity.ok(utilisateurService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDtoSend>> getAll() {
        return ResponseEntity.ok(utilisateurService.getAll());
    }

    @PostMapping
    public ResponseEntity<UtilisateurDtoSend> create(@RequestBody UtilisateurDtoReceive utilisateurDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(utilisateurService.save(utilisateurDtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        utilisateurService.delete(id);
        return ResponseEntity.ok("Utilisateur supprimer");
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDtoSend> update(@PathVariable int id, @RequestBody UtilisateurDtoReceive utilisateurDtoReceive) {
        return ResponseEntity.ok(utilisateurService.update(id, utilisateurDtoReceive));
    }


}