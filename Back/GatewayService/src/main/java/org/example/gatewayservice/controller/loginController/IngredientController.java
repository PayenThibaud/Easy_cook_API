package org.example.gatewayservice.controller.loginController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.IngredientDto.IngredientDtoRequest;
import org.example.gatewayservice.dto.IngredientDto.IngredientDtoResponse;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("ingredient")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class IngredientController {
    private ObjectMapper om;

    public IngredientController() {
        this.om = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDtoResponse> getIngredientById(@PathVariable int id) {
        RestClient<IngredientDtoResponse> ingredientRestClient = new RestClient<>("http://localhost:" + PortAPI.portIngredient + "/ingredient/" + id);
        IngredientDtoResponse ingredientDtoResponse = ingredientRestClient.getRequest(IngredientDtoResponse.class);
        return new ResponseEntity<>(ingredientDtoResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<IngredientDtoResponse>> getAllIngredient() {
        RestClient<IngredientDtoResponse[]> ingredientRestClient = new RestClient<>("http://localhost:" + PortAPI.portIngredient + "/ingredient");
        List<IngredientDtoResponse> ingredientDtoResponses = Arrays.stream(ingredientRestClient.getRequest(IngredientDtoResponse[].class)).toList();
        return new ResponseEntity<>(ingredientDtoResponses, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<IngredientDtoResponse> postIngredient(@RequestBody IngredientDtoRequest ingredientDtoRequest) throws JsonProcessingException {
        RestClient<IngredientDtoResponse> ingredientRestClient = new RestClient<>("http://localhost:" + PortAPI.portIngredient + "/ingredient");
        IngredientDtoResponse ingredientDtoResponse = ingredientRestClient.postRequest(om.writeValueAsString(ingredientDtoRequest), IngredientDtoResponse.class);
        return new ResponseEntity<>(ingredientDtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDtoResponse> updateIngredient(@PathVariable int id, @RequestBody IngredientDtoRequest ingredientDtoRequest) throws JsonProcessingException {
        RestClient<IngredientDtoResponse> ingredientRestClient = new RestClient<>("http://localhost:" + PortAPI.portIngredient + "/ingredient/" + id);
        IngredientDtoResponse ingredientDtoResponse = ingredientRestClient.putRequest(om.writeValueAsString(ingredientDtoRequest), IngredientDtoResponse.class);
        return new ResponseEntity<>(ingredientDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteIngredient(@PathVariable int id) {
        RestClient<Void> ingredientRestClient = new RestClient<>("http://localhost:" + PortAPI.portIngredient + "/ingredient/" + id);
        String responseMessage = "L'ingredient avec l'ID " + id + " a été supprimé.";
        ingredientRestClient.deleteRequest();
 
        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}

