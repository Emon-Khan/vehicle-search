package com.shikbeTumio.vehicle.api.vehiclesearch.service;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;

public interface VehicleMarketPriceService {
    VehicleMarketPrice saveVehicleMarketPrice(VehicleMarketPrice vehicleMarketPrice);
    VehicleMarketPrice getVehicleMarketPriceByBrandModel(String brandName, String modelName);
}
