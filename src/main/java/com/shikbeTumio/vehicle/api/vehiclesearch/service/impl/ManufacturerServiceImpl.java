package com.shikbeTumio.vehicle.api.vehiclesearch.service.impl;

import com.shikbeTumio.vehicle.api.vehiclesearch.dao.ManufacturerDAO;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.Manufacturer;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    @Autowired
    private ManufacturerDAO manufacturerDAO;
    @Override
    public Manufacturer saveManufacturer(Manufacturer manufacturer) {
        return manufacturerDAO.save(manufacturer);
    }

    @Override
    public List<Manufacturer> fetchAllManufacturers() {
        List<Manufacturer> allManufacturer = manufacturerDAO.findAll();
        return allManufacturer;
    }

    @Override
    public Manufacturer getManufacturerById(int id) {
        Optional<Manufacturer> manufacturerFromDbUsingId= manufacturerDAO.findById(id);
        if(manufacturerFromDbUsingId.isPresent()){
            return manufacturerFromDbUsingId.get();
        }
        return null;
    }
}
