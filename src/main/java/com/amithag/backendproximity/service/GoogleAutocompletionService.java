package com.amithag.backendproximity.service;

import com.amithag.backendproximity.api.response.GoogleAutoResponse;
import com.amithag.backendproximity.api.response.GoogleGetLatLngResponse;

public interface GoogleAutocompletionService {
 GoogleAutoResponse findPlaceByInput(String input);
 GoogleGetLatLngResponse findLatLngByPlaceId(String placeId);
}
