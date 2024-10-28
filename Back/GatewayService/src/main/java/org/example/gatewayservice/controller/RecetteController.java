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

@RestController
@RequestMapping("/recette")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class RecetteController {

    private ObjectMapper om;

    public RecetteController() {
        this.om = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetteDtoResponse> findRecetteById(@PathVariable int id) {
        RestClient<RecetteDtoResponse> recetteRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/recette/" + id);
        RecetteDtoResponse recetteDtoResponse = recetteRestClient.getRequest(RecetteDtoResponse.class);
        return new ResponseEntity<>(recetteDtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RecetteDtoResponse> postRecette(@RequestBody RecetteDtoRequest recetteDtoRequest) throws JsonProcessingException {
        RestClient<RecetteDtoResponse> recetteRestClient = new RestClient<>("http://localhost:" + PortAPI.portRecette + "/recette");
        RecetteDtoResponse recetteDtoResponse = recetteRestClient.postRequest(om.writeValueAsString(recetteDtoRequest), RecetteDtoResponse.class);
        return new ResponseEntity<>(recetteDtoResponse, HttpStatus.OK);
    }
}
