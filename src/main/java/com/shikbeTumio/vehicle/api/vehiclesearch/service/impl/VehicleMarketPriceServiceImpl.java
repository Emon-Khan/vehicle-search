package com.shikbeTumio.vehicle.api.vehiclesearch.service.impl;

import com.shikbeTumio.vehicle.api.vehiclesearch.dao.VehicleMarketPriceDAO;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.VehicleMarketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleMarketPriceServiceImpl implements VehicleMarketPriceService {
    @Autowired
    VehicleMarketPriceDAO vehicleMarketPriceDAO;
    @Override
    public VehicleMarketPrice saveVehicleMarketPrice(VehicleMarketPrice vehicleMarketPrice){
        return vehicleMarketPriceDAO.save(vehicleMarketPrice);
    }
    @Override
    public VehicleMarketPrice getVehicleMarketPriceByBrandModel(String brandName, String modelName) {
        return vehicleMarketPriceDAO.findByBrandNameAndModelName(brandName, modelName);
    }
}
