package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.AuthDto.LoginDtoRequest;
import org.example.gatewayservice.dto.AuthDto.LoginDtoResponse;
import org.example.gatewayservice.dto.AuthDto.RegisterDtoRequest;
import org.example.gatewayservice.dto.AuthDto.RegisterDtoResponse;
import org.example.gatewayservice.exception.AlreadyExistException;
import org.example.gatewayservice.exception.UserNotFoundException;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class AuthentificationController {
    private ObjectMapper om;

    public AuthentificationController() {
        this.om = new ObjectMapper();
    }

    @PostMapping("/register")
    public ResponseEntity<LoginDtoResponse> register (@RequestBody RegisterDtoRequest registerDtoRequest) throws JsonProcessingException, AlreadyExistException, UserNotFoundException {
        RestClient<RegisterDtoResponse> registerRestClient = new RestClient<>("http://localhost:"+ PortAPI.portAuth +"/auth/register");
        RegisterDtoResponse registerDtoResponse = registerRestClient.postRequest(om.writeValueAsString(registerDtoRequest), RegisterDtoResponse.class);
        if(registerDtoResponse.getId_utilisateurApp() != -1){
            LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerDtoRequest.getEmail(),registerDtoRequest.getPassword());
            return loginMethod(loginDtoRequest);
        }
        throw new AlreadyExistException("User");
    }


    @PostMapping("/login")
    public ResponseEntity<LoginDtoResponse> login (@RequestBody LoginDtoRequest loginDtoRequest) throws JsonProcessingException, UserNotFoundException {
        return loginMethod(loginDtoRequest);
    }

    private ResponseEntity<LoginDtoResponse> loginMethod (LoginDtoRequest loginDtoRequest) throws JsonProcessingException, UserNotFoundException {
        RestClient<LoginDtoResponse> loginRestClient = new RestClient<>("http://localhost:"+ PortAPI.portAuth +"/auth/login");
        LoginDtoResponse loginDtoResponse = loginRestClient.postRequest(om.writeValueAsString(loginDtoRequest), LoginDtoResponse.class);
        if(!loginDtoResponse.getToken().equals("NotFound")){
            return new ResponseEntity<>(loginDtoResponse, HttpStatus.OK);
        }
        throw new UserNotFoundException();
    }
}