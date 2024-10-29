package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.PrefAlimUserDto.PrefAlimUserDtoRequest;
import org.example.gatewayservice.dto.PrefAlimUserDto.PrefAlimUserDtoResponse;
import org.example.gatewayservice.utils.PortAPI;
import org.example.gatewayservice.tools.RestClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/prefalimuser")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class PrefAlimUserController {

    private ObjectMapper om;

    public PrefAlimUserController() {
        this.om = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PrefAlimUserDtoResponse>> getPrefAlimUserById(@PathVariable int id) {
        RestClient<PrefAlimUserDtoResponse[]> restClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/prefalimuser/" + id);
        List<PrefAlimUserDtoResponse> prefAlimUserDtoResponses = Arrays.stream(restClient.getRequest(PrefAlimUserDtoResponse[].class)).toList();
        return new ResponseEntity<>(prefAlimUserDtoResponses, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PrefAlimUserDtoResponse>> getAllPrefAlimUsers() {
        RestClient<PrefAlimUserDtoResponse[]> restClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/prefalimuser");
        List<PrefAlimUserDtoResponse> prefAlimUserDtoResponses = Arrays.stream(restClient.getRequest(PrefAlimUserDtoResponse[].class)).toList();
        return new ResponseEntity<>(prefAlimUserDtoResponses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PrefAlimUserDtoResponse> postPrefAlimUser(@RequestBody PrefAlimUserDtoRequest prefAlimUserDtoRequest) throws JsonProcessingException {
        RestClient<PrefAlimUserDtoResponse> prefAlimrestClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/prefalimuser");
        PrefAlimUserDtoResponse createdPrefAlimUser = prefAlimrestClient.postRequest(om.writeValueAsString(prefAlimUserDtoRequest), PrefAlimUserDtoResponse.class);
        return new ResponseEntity<>(createdPrefAlimUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{utilisateurid}")
    public ResponseEntity<Void> deletePrefAlimByUserId(@PathVariable int utilisateurId) {
        RestClient<Void> restClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/prefalimuser/" + utilisateurId);
        restClient.deleteRequest();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
