package org.example.frigoservice.controller;

import org.example.frigoservice.dto.FrigoDtoReceive;
import org.example.frigoservice.dto.FrigoDtoSend;
import org.example.frigoservice.service.FrigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/frigo")
public class FrigoController {
    @Autowired
    private FrigoService frigoService;

    @GetMapping("/{id}")
    public ResponseEntity<FrigoDtoSend> getById(@PathVariable int id) {
        return ResponseEntity.ok(frigoService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<FrigoDtoSend>> getAll() {
        return ResponseEntity.ok(frigoService.getAll());
    }

    @PostMapping
    public ResponseEntity<FrigoDtoSend> create(@RequestBody FrigoDtoReceive frigoDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(frigoService.save(frigoDtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        frigoService.delete(id);
        return ResponseEntity.ok("frigo supprimer");
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrigoDtoSend> update(@PathVariable int id, @RequestBody FrigoDtoReceive frigoDtoReceive) {
        return ResponseEntity.ok(frigoService.update(id, frigoDtoReceive));
    }
}
