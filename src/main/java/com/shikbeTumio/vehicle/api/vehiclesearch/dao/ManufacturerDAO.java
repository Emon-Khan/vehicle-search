package com.shikbeTumio.vehicle.api.vehiclesearch.dao;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerDAO extends JpaRepository<Manufacturer, Integer> {
    Manufacturer findByManufacturerName(String name);
}
