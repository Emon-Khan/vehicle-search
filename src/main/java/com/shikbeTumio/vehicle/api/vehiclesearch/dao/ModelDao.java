package com.shikbeTumio.vehicle.api.vehiclesearch.dao;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModelDao extends JpaRepository<Model, Integer> {
    List<Model> findByManufacturer(Manufacturer manufacturer);
}
