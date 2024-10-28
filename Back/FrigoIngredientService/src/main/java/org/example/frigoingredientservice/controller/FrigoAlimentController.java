package org.example.frigoingredientservice.controller;

import org.example.frigoingredientservice.dto.FrigoAlimentDtoReceive;
import org.example.frigoingredientservice.dto.FrigoAlimentDtoSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.frigoingredientservice.service.FrigoAlimentService;

import java.util.List;

@RestController
@RequestMapping("/frigo-aliment")
public class FrigoAlimentController {
    @Autowired
    private FrigoAlimentService frigoAlimentService;

    @GetMapping("/{id}")
    public ResponseEntity<FrigoAlimentDtoSend> getById(@PathVariable int id) {
        return ResponseEntity.ok(frigoAlimentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<FrigoAlimentDtoSend>> getAll() {
        return ResponseEntity.ok(frigoAlimentService.getAll());
    }

    @PostMapping
    public ResponseEntity<FrigoAlimentDtoSend> create(@RequestBody FrigoAlimentDtoReceive frigoAlimentDtoReceive) {
        return ResponseEntity.status(HttpStatus.CREATED).body(frigoAlimentService.save(frigoAlimentDtoReceive));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        frigoAlimentService.delete(id);
        return ResponseEntity.ok("frigo-aliment supprimer");
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrigoAlimentDtoSend> update(@PathVariable int id, @RequestBody FrigoAlimentDtoReceive frigoAlimentDtoReceive) {
        return ResponseEntity.ok(frigoAlimentService.update(id, frigoAlimentDtoReceive));
    }
}
