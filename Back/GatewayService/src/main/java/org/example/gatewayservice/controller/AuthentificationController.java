package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.gatewayservice.dto.AuthDto.LoginDtoRequest;
import org.example.gatewayservice.dto.AuthDto.LoginDtoResponse;
import org.example.gatewayservice.dto.AuthDto.RegisterDtoRequest;
import org.example.gatewayservice.dto.AuthDto.RegisterDtoResponse;
import org.example.gatewayservice.dto.UtilisateurDto.UtilisateurDtoRequest;
import org.example.gatewayservice.dto.UtilisateurDto.UtilisateurDtoResponse;
import org.example.gatewayservice.exception.AlreadyExistException;
import org.example.gatewayservice.exception.UserNotFoundException;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", methods = {RequestMethod.POST})
public class AuthentificationController {

    private ObjectMapper om;

    public AuthentificationController() {
        this.om = new ObjectMapper();
    }

    @PostMapping("/register")
    public ResponseEntity<LoginDtoResponse> register(@RequestBody RegisterDtoRequest registerDtoRequest) throws JsonProcessingException, AlreadyExistException, UserNotFoundException {
        RestClient<RegisterDtoResponse> registerRestClient = new RestClient<>("http://localhost:" + PortAPI.portAuth + "/api/auth/register");
        RegisterDtoResponse registerDtoResponse = registerRestClient.postRequest(om.writeValueAsString(registerDtoRequest), RegisterDtoResponse.class);
        if (registerDtoResponse.getId() != -1) {
            LoginDtoRequest loginDtoRequest = new LoginDtoRequest(registerDtoRequest.getEmail(), registerDtoRequest.getPassword());
            return loginMethod(loginDtoRequest);
        }
        throw new AlreadyExistException("User");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDtoResponse> login(@RequestBody LoginDtoRequest loginDtoRequest) throws JsonProcessingException, UserNotFoundException {
        return loginMethod(loginDtoRequest);
    }

    private ResponseEntity<LoginDtoResponse> loginMethod(LoginDtoRequest loginDtoRequest) throws JsonProcessingException, UserNotFoundException {
        RestClient<LoginDtoResponse> loginRestClient = new RestClient<>("http://localhost:" + PortAPI.portAuth + "/api/auth/login");
        LoginDtoResponse loginDtoResponse = loginRestClient.postRequest(om.writeValueAsString(loginDtoRequest), LoginDtoResponse.class);

        if (!loginDtoResponse.getToken().equals("NotFound")) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            session.setAttribute("token", loginDtoResponse.getToken());

            return new ResponseEntity<>(loginDtoResponse, HttpStatus.OK);
        }
        throw new UserNotFoundException();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("token");
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> getUtilisateurById (@PathVariable int id){
        RestClient<UtilisateurDtoResponse> utilisateurRestClient = new RestClient<>("http://localhost:"+ PortAPI.portAuth + "/api/auth/"+id);
        UtilisateurDtoResponse utilisateurDtoResponse = utilisateurRestClient.getRequest(UtilisateurDtoResponse.class);
        return new ResponseEntity<>(utilisateurDtoResponse, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UtilisateurDtoResponse>> getAllUtilisateur() {
        // route du micro service utilisateur
        RestClient<UtilisateurDtoResponse[]> utilisateurRestClient = new RestClient<>("http://localhost:" + PortAPI.portAuth + "/api/auth");
        // recuperation
        List<UtilisateurDtoResponse> utilisateurDtoResponses = Arrays.stream(utilisateurRestClient.getRequest(UtilisateurDtoResponse[].class)).toList();
        return new ResponseEntity<>(utilisateurDtoResponses, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UtilisateurDtoResponse> updateUtilisateur(@PathVariable int id, @RequestBody UtilisateurDtoRequest utilisateurDtoRequest) throws JsonProcessingException {
        RestClient<UtilisateurDtoResponse> utilisateurRestClient = new RestClient<>("http://localhost:" + PortAPI.portAuth + "/api/auth/" + id);
        UtilisateurDtoResponse utilisateurDtoResponse = utilisateurRestClient.putRequest(om.writeValueAsString(utilisateurDtoRequest), UtilisateurDtoResponse.class);
        return new ResponseEntity<>(utilisateurDtoResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUtilisateur(@PathVariable int id) {
        RestClient<Void> utilisateurRestClient = new RestClient<>("http://localhost:" + PortAPI.portAuth + "/api/auth/" + id);
        String responseMessage = "L'utilisateur avec l'ID " + id + " a été supprimé.";
        utilisateurRestClient.deleteRequest();

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);
    }
}
