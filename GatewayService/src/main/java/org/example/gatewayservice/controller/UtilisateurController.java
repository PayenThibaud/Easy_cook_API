package org.example.gatewayservice.controller;

import org.example.gatewayservice.dto.UtilisateurDto.UtilisateurDtoRequest;
import org.example.gatewayservice.dto.UtilisateurDto.UtilisateurDtoResponse;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class UtilisateurController {
    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> findUserById (@PathVariable int id){
        RestClient<UtilisateurDtoResponse> utilisateurRestClient = new RestClient<>("http://localhost:"+ PortAPI.portUtilisateur +"/utilisateur/"+id);
        UtilisateurDtoResponse utilisateurDtoResponse = utilisateurRestClient.getRequest(UtilisateurDtoResponse.class);
        return new ResponseEntity<>(utilisateurDtoResponse, HttpStatus.OK);
    }
}