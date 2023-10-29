package com.shikbeTumio.vehicle.api.vehiclesearch.controller;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturers")
public class manufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;
    @PostMapping
    public ResponseEntity<Manufacturer> createManufacturerInDB(@RequestBody Manufacturer manufacturer){
        Manufacturer dbRecord = manufacturerService.saveManufacturer(manufacturer);
        return new ResponseEntity<>(dbRecord, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Manufacturer>> getAllManufacturer(){
        List<Manufacturer> allManufacturers = manufacturerService.fetchAllManufacturers();
        return ResponseEntity.status(HttpStatus.OK).body(allManufacturers);
    }
}
