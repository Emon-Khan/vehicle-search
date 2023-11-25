package com.shikbeTumio.vehicle.api.vehiclesearch.service.impl;

import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetails;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetailsDTO;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.VehicleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {
    @Autowired
    public RestTemplate restTemplate;
    @Override
    public VehicleDetailsDTO getAllVehicleDetails() {
        return restTemplate.getForObject("http://localhost:9192/api/v1/vehicle-details", VehicleDetailsDTO.class);
    }
}
