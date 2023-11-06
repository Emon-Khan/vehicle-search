package com.shikbeTumio.vehicle.api.vehiclesearch.service;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.ManufacturerNotFoundException;

import java.util.List;

public interface ManufacturerService {
    Manufacturer saveManufacturer(Manufacturer manufacturer);
    List<Manufacturer> fetchAllManufacturers();
    Manufacturer getManufacturerById(int id);
    Manufacturer updateManufacturer(int id, Manufacturer updatedManufacturer);
    void deleteManufacturerByID(int id) throws ManufacturerNotFoundException;
}
