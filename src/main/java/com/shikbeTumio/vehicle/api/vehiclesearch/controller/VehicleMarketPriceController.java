package com.shikbeTumio.vehicle.api.vehiclesearch.controller;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.VehicleMarketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/vehicle-market-price")
public class VehicleMarketPriceController {
    @Autowired
    private VehicleMarketPriceService vehicleMarketPriceService;

    @PostMapping
    public ResponseEntity<VehicleMarketPrice> saveVehicleMarketPrice(@RequestBody VehicleMarketPrice vehicleMarketPrice) {
        VehicleMarketPrice saveVehicleMarketPrice = vehicleMarketPriceService.saveVehicleMarketPrice(vehicleMarketPrice);
        return new ResponseEntity<>(saveVehicleMarketPrice, HttpStatus.CREATED);
    }
}
