package com.amithag.backendproximity.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class GoogleGetLatLngResponse {
    public Result result;
    public String status;

    public static class Result{
        public Geometry geometry;
        public String name;
        @JsonProperty("place_id")
        public String placeId;
    }

    public static class Geometry{
        public Location location;
    }

    public static class Location{
        public double lat;
        public double lng;
    }



}
