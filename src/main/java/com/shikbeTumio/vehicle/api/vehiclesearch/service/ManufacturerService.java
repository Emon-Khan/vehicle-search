package com.shikbeTumio.vehicle.api.vehiclesearch.service;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;

import java.util.List;

public interface ManufacturerService {
    Manufacturer saveManufacturer(Manufacturer manufacturer);
    List<Manufacturer> fetchAllManufacturers();
}
