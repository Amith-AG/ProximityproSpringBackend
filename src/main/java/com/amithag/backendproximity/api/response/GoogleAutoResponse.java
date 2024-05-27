package com.amithag.backendproximity.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class GoogleAutoResponse {
    public ArrayList<Prediction> predictions;
    public String status;

    public static class Prediction{
        public String description;
        @JsonProperty("place_id")
        public String placeId;
    }

}








