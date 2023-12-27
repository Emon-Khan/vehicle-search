package com.shikbeTumio.vehicle.api.vehiclesearch.service;

import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;

public interface VehicleMarketPriceService {
    VehicleMarketPrice saveVehicleMarketPrice(VehicleMarketPrice vehicleMarketPrice);
    VehicleMarketPrice getVehicleMarketPriceByBrandAndModelAndTrimAndYear(String brandName, String modelName, String trimType, int modelYear);
}
