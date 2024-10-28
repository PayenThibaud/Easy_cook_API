package org.example.gatewayservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.gatewayservice.dto.ContrainteAlimentaireDto.ContraintDtoResponse;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.gatewayservice.tools.RestClient;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("contrainte")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class ContrainteController {
    private ObjectMapper om;
    public ContrainteController() {
        this.om = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContraintDtoResponse> getContrainteById (@PathVariable int id) {
        RestClient<ContraintDtoResponse> contrainteRestClient = new RestClient<>("http://localhost:"+ PortAPI.portContrainteAlim +"/contrainte/"+id);
        ContraintDtoResponse contraintDtoResponse = contrainteRestClient.getRequest(ContraintDtoResponse.class);
        return new ResponseEntity<>(contraintDtoResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ContraintDtoResponse>> getAllContrainte() {
        RestClient<ContraintDtoResponse[]> contrainteRestClient = new RestClient<>("http://localhost:"+ PortAPI.portContrainteAlim+"/contrainte");
        List<ContraintDtoResponse> contraintDtoResponses = Arrays.stream(contrainteRestClient.getRequest(ContraintDtoResponse[].class)).toList();
        return new ResponseEntity<>(contraintDtoResponses, HttpStatus.OK);
    }
}
