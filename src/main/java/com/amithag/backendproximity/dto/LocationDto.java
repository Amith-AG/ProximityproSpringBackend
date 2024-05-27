package com.amithag.backendproximity.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class LocationDto {
    private Double lat;
    private Double lng;

}
