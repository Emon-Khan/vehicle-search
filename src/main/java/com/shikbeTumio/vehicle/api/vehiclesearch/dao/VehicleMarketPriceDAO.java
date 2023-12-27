package com.shikbeTumio.vehicle.api.vehiclesearch.dao;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleMarketPriceDAO extends JpaRepository<VehicleMarketPrice, Integer> {
    VehicleMarketPrice findByBrandNameAndModelNameAndTrimTypeAndModelYear(String brandName, String modelName, String trimType, int modelYear);
}
