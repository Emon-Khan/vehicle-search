package com.shikbeTumio.vehicle.api.vehiclesearch.controller;

import com.shikbeTumio.vehicle.api.vehiclesearch.dto.ClientVehicleDetail;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetails;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.VehicleDetailsNotFound;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicle-details")
public class VehicleDetailsController {
    @Autowired
    private VehicleDetailService vehicleDetailService;

    @GetMapping
    public ResponseEntity<List<ClientVehicleDetail>> getAllClientVehicleDetailsController() {
        List<ClientVehicleDetail> dbVehicles = vehicleDetailService.getAllClientVehicleDetails();
        return new ResponseEntity<>(dbVehicles, HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleDetails> getVehicleById(@PathVariable int vehicleId) throws VehicleDetailsNotFound {
        return new ResponseEntity<>(vehicleDetailService.getVehicleById(vehicleId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientVehicleDetail>> searchVehicleByFilterCriteria(@RequestParam String modelYear, @RequestParam String brand, @RequestParam String model, @RequestParam String trim, @RequestParam String price) {
        List<ClientVehicleDetail> filteredClientVehicleDetailsList = vehicleDetailService.fetchVehicleDetailsByCriteria(modelYear, brand, model, trim, price);
        return new ResponseEntity<>(filteredClientVehicleDetailsList, HttpStatus.OK);
    }
}
