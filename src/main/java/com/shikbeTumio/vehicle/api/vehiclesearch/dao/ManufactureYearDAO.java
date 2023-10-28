package com.shikbeTumio.vehicle.api.vehiclesearch.dao;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.ManufacturerYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufactureYearDAO extends JpaRepository<ManufacturerYear, Integer> {
}
