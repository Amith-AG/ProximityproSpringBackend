package com.amithag.backendproximity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
public class SpecialistNoRoleDto extends LocationDto {
    private long id;
    private String name;
    private String place;
    private String email;
    private String phone;
    private List<String> roles;
}
