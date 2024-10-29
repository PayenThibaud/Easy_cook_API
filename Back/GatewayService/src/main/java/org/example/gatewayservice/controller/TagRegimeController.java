package org.example.gatewayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.gatewayservice.dto.PrefAlimUserDto.PrefAlimUserDtoResponse;
import org.example.gatewayservice.dto.TagRegimeDto.TagRegimeDtoRequest;
import org.example.gatewayservice.dto.TagRegimeDto.TagRegimeDtoResponse;
import org.example.gatewayservice.tools.RestClient;
import org.example.gatewayservice.utils.PortAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/tagregime")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET})
public class TagRegimeController {
    private ObjectMapper om;

    public TagRegimeController() {
        this.om = new ObjectMapper();
    }

    @GetMapping
    public ResponseEntity<List<TagRegimeDtoResponse>> getAllTagRegimes() {
        RestClient<TagRegimeDtoResponse[]> restClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/tagregime");
        List<TagRegimeDtoResponse> tagRegimeDtoResponses = Arrays.stream(restClient.getRequest(TagRegimeDtoResponse[].class)).toList();
        return new ResponseEntity<>(tagRegimeDtoResponses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagRegimeDtoResponse> getTagRegimeByIdContrainte(@PathVariable int id) {
        RestClient<TagRegimeDtoResponse> restClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/tagregime/" + id);
        TagRegimeDtoResponse tagRegimeDtoResponse = restClient.getRequest(TagRegimeDtoResponse.class);
        return new ResponseEntity<>(tagRegimeDtoResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TagRegimeDtoResponse> PostTagRegime(@RequestBody TagRegimeDtoRequest tagRegimeDtoRequest)throws JsonProcessingException {
        RestClient<TagRegimeDtoResponse> tagRegimeDtoResponseRestClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/tagregime");
        TagRegimeDtoResponse createTagRegime = tagRegimeDtoResponseRestClient.postRequest(om.writeValueAsString(tagRegimeDtoRequest), TagRegimeDtoResponse.class);
        return new ResponseEntity<>(createTagRegime, HttpStatus.CREATED);
    }

    @DeleteMapping("/{recetteid}")
    public ResponseEntity<Void> DeleteTagRegime(@PathVariable int recetteid) {
        RestClient<Void> restClient = new RestClient<>("http://localhost:" + PortAPI.portContrainteAlim + "/tagregime/" + recetteid);
        restClient.deleteRequest();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
