package com.shikbeTumio.vehicle.api.vehiclesearch.controller;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.ManufacturerNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.MissingFieldException;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ManufacturerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/manufacturers")
public class manufacturerController {
    @Autowired
    private ManufacturerService manufacturerService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('admin:create', 'user:create')")
    public ResponseEntity<Manufacturer> createManufacturerInDB(@Valid @RequestBody Manufacturer manufacturer, BindingResult result) throws MissingFieldException {
        if (result.hasErrors()) {
            List<ObjectError> error = result.getAllErrors();
            throw new MissingFieldException(error.get(0).getDefaultMessage());
        }
        Manufacturer dbRecord = manufacturerService.saveManufacturer(manufacturer);
        return new ResponseEntity<>(dbRecord, HttpStatus.CREATED);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('admin:read', 'user:read')")
    public ResponseEntity<List<Manufacturer>> getAllManufacturer() {
        List<Manufacturer> allTheManufacturers = manufacturerService.fetchAllManufacturers();
        return ResponseEntity.status(HttpStatus.OK).body(allTheManufacturers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:read', 'user:read')")
    public ResponseEntity<Manufacturer> getManufacturerBasedOnId(@PathVariable int id) throws ManufacturerNotFoundException {
        Manufacturer manufacturerResultUsingId = manufacturerService.getManufacturerById(id);
        if (manufacturerResultUsingId == null) {
            throw new ManufacturerNotFoundException("No manufacturer found for ID- " + id);
        }
        return ResponseEntity.ok(manufacturerResultUsingId);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('admin:update', 'user:update')")
    public ResponseEntity<Manufacturer> updateManufacturer(@PathVariable int id, @Valid @RequestBody Manufacturer manufacturer, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            /*for(ObjectError var:errors){
                System.out.println("*************Errors********* "+var);
            }*/
            throw new MissingFieldException(errors.get(0).getDefaultMessage());
        }
        Manufacturer updatedManufacturer = manufacturerService.updateManufacturer(id, manufacturer);
        if (updatedManufacturer == null) {
            throw new ManufacturerNotFoundException("No manufacturer found for ID- " + id);
        }
        return new ResponseEntity<>(updatedManufacturer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin:delete')")
    public ResponseEntity<String> deleteManufacturer(@PathVariable int id) throws ManufacturerNotFoundException {
        manufacturerService.deleteManufacturerByID(id);
        return new ResponseEntity<>("Manufacturer deleted with the id "+id, HttpStatus.OK);
    }
}
