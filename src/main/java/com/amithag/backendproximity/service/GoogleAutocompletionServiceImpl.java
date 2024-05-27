package com.amithag.backendproximity.service;

import com.amithag.backendproximity.api.response.GoogleAutoResponse;
import com.amithag.backendproximity.api.response.GoogleGetLatLngResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GoogleAutocompletionServiceImpl implements GoogleAutocompletionService {
    @Value("${googleApiKey}")
    private String googleApiKey;
    @Autowired
    RestTemplate restTemplate;
    private String apiEndpoint;

    @Override
    public GoogleAutoResponse findPlaceByInput(String input) {
//        System.out.println(googleApiKey);
        apiEndpoint=String.format("https://maps.googleapis.com/maps/api/place/autocomplete/json?input=%s&types=geocode&components=country:in&key=%s",input,googleApiKey);

       ResponseEntity<GoogleAutoResponse> response= restTemplate.exchange(apiEndpoint, HttpMethod.GET,null, GoogleAutoResponse.class);
        GoogleAutoResponse body=response.getBody();
        return body;
    }

    @Override
    public GoogleGetLatLngResponse findLatLngByPlaceId(String placeId) {
        String endpoint=String.format("https://maps.googleapis.com/maps/api/place/details/json?placeid=%s&key=%s",placeId,googleApiKey);
        ResponseEntity<GoogleGetLatLngResponse> response=restTemplate.exchange(endpoint,HttpMethod.GET,null,GoogleGetLatLngResponse.class);
        GoogleGetLatLngResponse body=response.getBody();
        System.out.println(body);
        return body;
    }
}
