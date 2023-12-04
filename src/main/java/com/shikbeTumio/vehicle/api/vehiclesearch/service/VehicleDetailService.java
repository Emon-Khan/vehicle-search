package com.shikbeTumio.vehicle.api.vehiclesearch.service;

import com.shikbeTumio.vehicle.api.vehiclesearch.dto.ClientVehicleDetail;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetails;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetailsDTO;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.VehicleDetailsNotFound;

import java.util.List;

public interface VehicleDetailService {
    List<ClientVehicleDetail> getAllClientVehicleDetails();
    VehicleDetails getVehicleById(int vehicleId) throws VehicleDetailsNotFound;
}
