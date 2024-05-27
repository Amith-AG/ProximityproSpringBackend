package com.amithag.backendproximity.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@ToString
@Table(name="specialist")
public class Specialist{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String place;
    @Column(unique = true)
    private String email;
    private String phone;
    @Column(name = "location", nullable = false, columnDefinition = "Point")
    private Point location;
    @ManyToMany(fetch =FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(
            name = "specialist_role",
            joinColumns={
                  @JoinColumn(name = "specialist_id",referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id",referencedColumnName = "id")
            }
    )
    @JsonManagedReference
    private Set<Role> roles;

}
