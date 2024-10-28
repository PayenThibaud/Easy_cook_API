package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.FrigoDto.FrigoDtoRequest;
import org.example.gatewayservice.dto.FrigoDto.FrigoDtoResponse;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("frigo")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class FrigoController {
    private ObjectMapper om;

    public FrigoController() {
        this.om = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrigoDtoResponse> getFrigoById (@PathVariable int id){
        RestClient<FrigoDtoResponse> frigoRestClient = new RestClient<>("http://localhost:"+ PortAPI.portFrigo +"/frigo/"+id);
        FrigoDtoResponse frigoDtoResponse = frigoRestClient.getRequest(FrigoDtoResponse.class);
        return new ResponseEntity<>(frigoDtoResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FrigoDtoResponse>> getAllFrigo() {
        RestClient<FrigoDtoResponse[]> frigoRestClient = new RestClient<>("http://localhost:" + PortAPI.portFrigo + "/frigo");
        List<FrigoDtoResponse> frigoDtoResponses = Arrays.stream(frigoRestClient.getRequest(FrigoDtoResponse[].class)).toList();
        return new ResponseEntity<>(frigoDtoResponses, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<FrigoDtoResponse> postFrigo (@RequestBody FrigoDtoRequest frigoDtoRequest) throws JsonProcessingException {
        RestClient<FrigoDtoResponse> frigoRestClient = new RestClient<>("http://localhost:"+ PortAPI.portFrigo +"/frigo");
        FrigoDtoResponse frigoDtoResponse = frigoRestClient.postRequest(om.writeValueAsString(frigoDtoRequest),FrigoDtoResponse.class);
        return new ResponseEntity<>(frigoDtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrigoDtoResponse> updateFrigo(@PathVariable int id, @RequestBody FrigoDtoRequest frigoDtoRequest) throws JsonProcessingException {
        RestClient<FrigoDtoResponse> frigoRestClient = new RestClient<>("http://localhost:" + PortAPI.portFrigo + "/frigo/" + id);
        FrigoDtoResponse frigoDtoResponse = frigoRestClient.putRequest(om.writeValueAsString(frigoDtoRequest), FrigoDtoResponse.class);
        return new ResponseEntity<>(frigoDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFrigo(@PathVariable int id) {
        RestClient<Void> frigoRestClient = new RestClient<>("http://localhost:" + PortAPI.portFrigo + "/frigo/" + id);
        String responseMessage = "L'frigo avec l'ID " + id + " a été supprimé.";
        frigoRestClient.deleteRequest();

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
