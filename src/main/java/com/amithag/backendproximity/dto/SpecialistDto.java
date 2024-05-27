package com.amithag.backendproximity.dto;
import com.amithag.backendproximity.model.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
public class SpecialistDto extends LocationDto {
    private long id;
    private String name;
    private String place;
    private String email;
    private String phone;
    private Set<Map<String,String>>roles; //to insert specialist with roles
}
