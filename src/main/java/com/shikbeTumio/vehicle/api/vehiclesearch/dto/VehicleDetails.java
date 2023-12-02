package com.shikbeTumio.vehicle.api.vehiclesearch.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDetails {

    private int id;
    private int modelYear;
    @NotBlank(message = "* Manufacturer name is required")
    private String brandName;
    @NotBlank(message = "* Model name is required")
    @Size(min = 3, max = 15, message = "* Model name should be between 3-15 characters")
    private String modelName;
    private String trimType;
    private String bodyType;
    private double vehiclePrice;
    private int milesOnVehicle;
    private double interestRate;
    private String locationOfVehicle;
    private String sellerName;
    private String sellerContactNumber;
}
