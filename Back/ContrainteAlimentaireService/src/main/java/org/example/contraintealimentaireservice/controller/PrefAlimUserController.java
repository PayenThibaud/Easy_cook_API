package org.example.contraintealimentaireservice.controller;

import org.example.contraintealimentaireservice.dto.PrefAlimUserDTOReceive;
import org.example.contraintealimentaireservice.dto.PrefAlimUserDTOSend;
import org.example.contraintealimentaireservice.service.PrefAlimUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prefalimuser")
public class PrefAlimUserController {

    @Autowired
    private PrefAlimUserService prefAlimUserService;

    @GetMapping
    public ResponseEntity<List<PrefAlimUserDTOSend>> getAll() {
        return ResponseEntity.ok(prefAlimUserService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PrefAlimUserDTOSend>>getById(@PathVariable int id) {
        return ResponseEntity.ok(prefAlimUserService.getByIdUser(id));
    }

    @PostMapping
    public ResponseEntity<PrefAlimUserDTOSend> createPrefAlimUser(@RequestBody PrefAlimUserDTOReceive prefAlimUserDTOReceive) {
        PrefAlimUserDTOSend createdPrefAlimUser = prefAlimUserService.createPrefAlimUser(prefAlimUserDTOReceive);
        return new ResponseEntity<>(createdPrefAlimUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deletePrefAlimByUserId(@PathVariable int userId) {
        prefAlimUserService.deleteAllByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
