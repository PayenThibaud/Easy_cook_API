package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.UtilisateurDto.UtilisateurDtoRequest;
import org.example.gatewayservice.dto.UtilisateurDto.UtilisateurDtoResponse;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("utilisateur")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class UtilisateurController {

    private ObjectMapper om;

    public UtilisateurController() {
        this.om = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> findUtilisateurById (@PathVariable int id){
        RestClient<UtilisateurDtoResponse> utilisateurRestClient = new RestClient<>("http://localhost:"+ PortAPI.portUtilisateur +"/utilisateur/"+id);
        UtilisateurDtoResponse utilisateurDtoResponse = utilisateurRestClient.getRequest(UtilisateurDtoResponse.class);
        return new ResponseEntity<>(utilisateurDtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UtilisateurDtoResponse> postUtilisateur (@RequestBody UtilisateurDtoRequest utilisateurDtoRequest) throws JsonProcessingException {
        RestClient<UtilisateurDtoResponse> utilisateurRestClient = new RestClient<>("http://localhost:"+ PortAPI.portUtilisateur +"/utilisateur");
        UtilisateurDtoResponse utilisateurDtoResponse = utilisateurRestClient.postRequest(om.writeValueAsString(utilisateurDtoRequest),UtilisateurDtoResponse.class);
        return new ResponseEntity<>(utilisateurDtoResponse, HttpStatus.OK);
    }
}