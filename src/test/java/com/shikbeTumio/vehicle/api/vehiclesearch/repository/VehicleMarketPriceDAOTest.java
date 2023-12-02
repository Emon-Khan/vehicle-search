package com.shikbeTumio.vehicle.api.vehiclesearch.repository;

import com.shikbeTumio.vehicle.api.vehiclesearch.dao.VehicleMarketPriceDAO;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetails;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetailsDTO;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
//Configure in a Different way
//Embedded Database
@ActiveProfiles("test")
public class VehicleMarketPriceDAOTest {
    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    VehicleMarketPriceDAO vehicleMarketPriceDAO;

    @Test
    public void VehicleMarketPriceDAO_Save_ReturnedSavedVehicleMarketPrice() {
        //Arrange
        VehicleMarketPrice vehicleMarketPrice = new VehicleMarketPrice(1, 2022, "Honda", "Amaze", 24658);

        //Act
        VehicleMarketPrice savedVehicleMarketPrice = vehicleMarketPriceDAO.save(vehicleMarketPrice);

        //Assert
        assertThat(savedVehicleMarketPrice).usingRecursiveComparison().ignoringFields("id").isEqualTo(vehicleMarketPrice);
    }

    @Test
    public void VehicleMarketPrice_FindByBrandAndModelName() {
        //VehicleDetails vehicleDetails = new VehicleDetails(1, 2022, "Toyota", "Camry", "LS", "", 24268.65, 1500, 5.35, "Albuquerque, New Mexico", "DS Auto", "+8801967899852");
        VehicleMarketPrice vehicleMarketPrice = new VehicleMarketPrice(1, 2022, "Toyota", "Corolla", 23580);
        vehicleMarketPriceDAO.save(vehicleMarketPrice);
        VehicleDetailsDTO vehicleDetailsDTO = restTemplate.getForObject("http://localhost:9192/api/v1/vehicle-details", VehicleDetailsDTO.class);
        VehicleMarketPrice vehicleMarketPriceReturn = null;
        assert vehicleDetailsDTO != null;
        for (VehicleDetails vehicleDetails1 : vehicleDetailsDTO.getVehicleDetailsList()) {
            vehicleMarketPriceReturn = vehicleMarketPriceDAO.findByBrandNameAndModelName(vehicleDetails1.getBrandName(), vehicleDetails1.getModelName());
            if(vehicleMarketPriceReturn!=null){
                break;
            }
        }
        assertThat(vehicleMarketPriceReturn).isEqualTo(vehicleMarketPrice);
    }
}
