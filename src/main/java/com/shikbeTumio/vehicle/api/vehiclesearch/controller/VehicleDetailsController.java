package com.shikbeTumio.vehicle.api.vehiclesearch.controller;

import com.shikbeTumio.vehicle.api.vehiclesearch.dto.ClientVehicleDetail;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetailsDTO;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailsController {
    @Autowired
    private VehicleDetailService vehicleDetailService;


    @GetMapping
    public ResponseEntity<List<ClientVehicleDetail>> getAllVehicleDetails() {
        List<ClientVehicleDetail> dbVehicles = vehicleDetailService.getAllVehicleDetails();
        return new ResponseEntity<>(dbVehicles, HttpStatus.OK);
    }
}
