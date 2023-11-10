package com.shikbeTumio.vehicle.api.vehiclesearch.dao;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelDao extends JpaRepository<Model, Integer> {
}
