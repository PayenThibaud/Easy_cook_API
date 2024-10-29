package org.example.gatewayservice.controller.loginController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.FrigoAlimentDto.FrigoAlimentDtoRequest;
import org.example.gatewayservice.dto.FrigoAlimentDto.FrigoAlimentDtoResponse;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("frigo-aliment")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class FrigoAlimentController {
    private ObjectMapper om;

    public FrigoAlimentController() {
        this.om = new ObjectMapper();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FrigoAlimentDtoResponse> getFrigoAlimentById (@PathVariable int id){
        RestClient<FrigoAlimentDtoResponse> frigoAlimentRestClient = new RestClient<>("http://localhost:"+ PortAPI.portFrigoAlim +"/frigo-aliment/"+id);
        FrigoAlimentDtoResponse frigoAlimentDtoResponse = frigoAlimentRestClient.getRequest(FrigoAlimentDtoResponse.class);
        return new ResponseEntity<>(frigoAlimentDtoResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FrigoAlimentDtoResponse>> getAllFrigoAlimentAliment() {
        RestClient<FrigoAlimentDtoResponse[]> frigoAlimentRestClient = new RestClient<>("http://localhost:" + PortAPI.portFrigoAlim + "/frigo-aliment");
        List<FrigoAlimentDtoResponse> frigoAlimentDtoResponses = Arrays.stream(frigoAlimentRestClient.getRequest(FrigoAlimentDtoResponse[].class)).toList();
        return new ResponseEntity<>(frigoAlimentDtoResponses, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<FrigoAlimentDtoResponse> postFrigoAliment (@RequestBody FrigoAlimentDtoRequest frigoAlimentDtoRequest) throws JsonProcessingException {
        RestClient<FrigoAlimentDtoResponse> frigoAlimentRestClient = new RestClient<>("http://localhost:"+ PortAPI.portFrigoAlim +"/frigo-aliment");
        FrigoAlimentDtoResponse frigoAlimentDtoResponse = frigoAlimentRestClient.postRequest(om.writeValueAsString(frigoAlimentDtoRequest),FrigoAlimentDtoResponse.class);
        return new ResponseEntity<>(frigoAlimentDtoResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FrigoAlimentDtoResponse> updateFrigoAliment(@PathVariable int id, @RequestBody FrigoAlimentDtoRequest frigoAlimentDtoRequest) throws JsonProcessingException {
        RestClient<FrigoAlimentDtoResponse> frigoAlimentRestClient = new RestClient<>("http://localhost:" + PortAPI.portFrigoAlim + "/frigo-aliment/" + id);
        FrigoAlimentDtoResponse frigoAlimentDtoResponse = frigoAlimentRestClient.putRequest(om.writeValueAsString(frigoAlimentDtoRequest), FrigoAlimentDtoResponse.class);
        return new ResponseEntity<>(frigoAlimentDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFrigoAliment(@PathVariable int id) {
        RestClient<Void> frigoAlimentRestClient = new RestClient<>("http://localhost:" + PortAPI.portFrigoAlim + "/frigo-aliment/" + id);
        String responseMessage = "Le frigo-aliment avec l'ID " + id + " a été supprimé.";
        frigoAlimentRestClient.deleteRequest();

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
