package org.example.contraintealimentaireservice.controller;

import org.example.contraintealimentaireservice.dto.TagRegimeDTOReceive;
import org.example.contraintealimentaireservice.dto.TagRegimeDTOSend;
import org.example.contraintealimentaireservice.service.TagRegimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tagregime")
public class TagRegimeController {

    @Autowired
    private TagRegimeService tagRegimeService;

    //READ ALL
    @GetMapping
    public ResponseEntity<List<TagRegimeDTOSend>> getAllTagRegimes() {
        return ResponseEntity.ok(tagRegimeService.geAll());
    }

    //READ ID_contrainteAlimentaire
    @GetMapping("/{contrainteId}")
    public ResponseEntity<List<TagRegimeDTOSend>> getTagRegimeByIdContrainte(@PathVariable int contrainteId) {
        return ResponseEntity.ok(tagRegimeService.getByContrainteAlimentaireId(contrainteId));
    }

    //CREATE
    @PostMapping
    public ResponseEntity<TagRegimeDTOSend> createTagRegime(@RequestBody TagRegimeDTOReceive tagRegimeDTOReceive) {
        TagRegimeDTOSend tagRegimeDTOSend = tagRegimeService.createTagRegime(tagRegimeDTOReceive);
        return new ResponseEntity<>(tagRegimeDTOSend, HttpStatus.CREATED);
    }

    //DELETE par ID_recette
    @DeleteMapping("/{recetteId}")
    public ResponseEntity<TagRegimeDTOSend> deleteTagRegime(@PathVariable int recetteId) {
        tagRegimeService.deleteAllByRecetteID(recetteId);
        return ResponseEntity.noContent().build();
    }
}
