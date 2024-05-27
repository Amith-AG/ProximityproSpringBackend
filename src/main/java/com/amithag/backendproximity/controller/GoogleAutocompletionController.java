package com.amithag.backendproximity.controller;


import com.amithag.backendproximity.api.response.GoogleAutoResponse;
import com.amithag.backendproximity.api.response.GoogleGetLatLngResponse;
import com.amithag.backendproximity.service.GoogleAutocompletionService;
import com.amithag.backendproximity.service.GoogleAutocompletionServiceImpl;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@CrossOrigin
@RequestMapping("/google")
public class GoogleAutocompletionController {
   @Autowired
    GoogleAutocompletionServiceImpl googleAutocompletionService;

   @GetMapping("/place/{input}")
    ResponseEntity<GoogleAutoResponse> getPlaceSuggestionByInput(@PathVariable String input){
       GoogleAutoResponse suggestion=googleAutocompletionService.findPlaceByInput(input);
       return new ResponseEntity<>(suggestion, HttpStatus.OK);
   }
   @GetMapping("/location/{placeId}")
    ResponseEntity<GoogleGetLatLngResponse> getLatLngByPlaceId(@PathVariable String placeId){
       GoogleGetLatLngResponse locationDetails=googleAutocompletionService.findLatLngByPlaceId(placeId);
       return new ResponseEntity<>(locationDetails,HttpStatus.OK);
   }
}
