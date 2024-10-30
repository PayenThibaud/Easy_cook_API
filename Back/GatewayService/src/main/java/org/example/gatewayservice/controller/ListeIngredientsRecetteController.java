package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.ListeIngredientsRecetteDto.ListeIngredientsRecetteDtoRequest;
import org.example.gatewayservice.dto.ListeIngredientsRecetteDto.ListeIngredientsRecetteDtoResponse;
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
@RequestMapping("/listeIngredients")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})

public class ListeIngredientsRecetteController {    private final ObjectMapper objectMapper;

    public ListeIngredientsRecetteController() {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListeIngredientsRecetteDtoResponse> getById(@PathVariable int id) {
        RestClient<ListeIngredientsRecetteDtoResponse> listeIngredientsRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/listeIngredients/" + id);
        ListeIngredientsRecetteDtoResponse response = listeIngredientsRestClient.getRequest(ListeIngredientsRecetteDtoResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ListeIngredientsRecetteDtoResponse>> getAll() {
        RestClient<ListeIngredientsRecetteDtoResponse[]> listeIngredientsRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/listeIngredients");
        List<ListeIngredientsRecetteDtoResponse> responses = Arrays.stream(listeIngredientsRestClient.getRequest(ListeIngredientsRecetteDtoResponse[].class)).toList();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ListeIngredientsRecetteDtoResponse> create(@RequestBody ListeIngredientsRecetteDtoRequest dtoRequest) throws JsonProcessingException {
        RestClient<ListeIngredientsRecetteDtoResponse> listeIngredientsRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/listeIngredients");
        ListeIngredientsRecetteDtoResponse response = listeIngredientsRestClient.postRequest(objectMapper.writeValueAsString(dtoRequest), ListeIngredientsRecetteDtoResponse.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListeIngredientsRecetteDtoResponse> update(@PathVariable int id, @RequestBody ListeIngredientsRecetteDtoRequest dtoRequest) throws JsonProcessingException {
        RestClient<ListeIngredientsRecetteDtoResponse> listeIngredientsRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/listeIngredients/" + id);
        ListeIngredientsRecetteDtoResponse response = listeIngredientsRestClient.putRequest(objectMapper.writeValueAsString(dtoRequest), ListeIngredientsRecetteDtoResponse.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        RestClient<Void> listeIngredientsRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/listeIngredients/" + id);
        listeIngredientsRestClient.deleteRequest();
        return new ResponseEntity<>("L'ingrédient de la liste avec l'ID " + id + " a été supprimé.", HttpStatus.OK);
    }
}

