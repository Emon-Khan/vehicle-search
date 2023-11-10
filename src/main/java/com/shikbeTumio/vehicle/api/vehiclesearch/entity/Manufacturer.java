package com.shikbeTumio.vehicle.api.vehiclesearch.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "manufacturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Name field can't be blank")
    @Column(name="manufacturer_name")
    private String manufacturerName;
    @NotBlank(message = "CountryOfOrigin field can't be blank")
    @Column(name="country_of_origin")
    private String countryOfOrigin;
}
