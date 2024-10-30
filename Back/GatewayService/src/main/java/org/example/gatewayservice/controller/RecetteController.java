package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.RecetteDto.RecetteDtoRequest;
import org.example.gatewayservice.dto.RecetteDto.RecetteDtoResponse;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/recette")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class RecetteController {

    private ObjectMapper objectMapper;

    public RecetteController() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetteDtoResponse> getRecetteById(@PathVariable int id) {
        RestClient<RecetteDtoResponse> recetteRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/recettes/" + id);
        RecetteDtoResponse recetteDtoResponse = recetteRestClient.getRequest(RecetteDtoResponse.class);
        return new ResponseEntity<>(recetteDtoResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RecetteDtoResponse>> getAllRecettes() {
        RestClient<RecetteDtoResponse[]> recetteRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/recettes");
        List<RecetteDtoResponse> recetteDtoResponses = Arrays.stream(recetteRestClient.getRequest(RecetteDtoResponse[].class)).toList();
        return new ResponseEntity<>(recetteDtoResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecetteDtoResponse> postRecette(@RequestBody RecetteDtoRequest recetteDtoRequest) throws JsonProcessingException {
        RestClient<RecetteDtoResponse> recetteRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/recettes");
        RecetteDtoResponse recetteDtoResponse = recetteRestClient.postRequest(objectMapper.writeValueAsString(recetteDtoRequest), RecetteDtoResponse.class);
        return new ResponseEntity<>(recetteDtoResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetteDtoResponse> updateRecette(@PathVariable int id, @RequestBody RecetteDtoRequest recetteDtoRequest) throws JsonProcessingException {
        RestClient<RecetteDtoResponse> recetteRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/recettes/" + id);
        RecetteDtoResponse recetteDtoResponse = recetteRestClient.putRequest(objectMapper.writeValueAsString(recetteDtoRequest), RecetteDtoResponse.class);
        return new ResponseEntity<>(recetteDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecette(@PathVariable int id) {
        RestClient<Void> recetteRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/recettes/" + id);
        recetteRestClient.deleteRequest();
        return new ResponseEntity<>("La recette avec l'ID " + id + " a été supprimée.", HttpStatus.OK);
    }
}