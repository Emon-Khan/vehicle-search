package com.shikbeTumio.vehicle.api.vehiclesearch.service.impl;

import com.shikbeTumio.vehicle.api.vehiclesearch.dto.ClientVehicleDetail;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetails;
import com.shikbeTumio.vehicle.api.vehiclesearch.dto.VehicleDetailsDTO;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;
import com.shikbeTumio.vehicle.api.vehiclesearch.exception.VehicleMarketPriceNotFoundException;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.VehicleDetailService;
import com.shikbeTumio.vehicle.api.vehiclesearch.service.VehicleMarketPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleDetailServiceImpl implements VehicleDetailService {
    @Autowired
    public RestTemplate restTemplate;
    @Autowired
    public VehicleMarketPriceService vehicleMarketPriceService;

    @Override
    public List<ClientVehicleDetail> getAllVehicleDetails() {
        VehicleDetailsDTO vehicleDetailsDTO = restTemplate.getForObject("http://localhost:9192/api/v1/vehicle-details", VehicleDetailsDTO.class);
        /*List<ClientVehicleDetail> clientVehicleDetailsList = new ArrayList<>();
        for (VehicleDetails vehicleDetails : vehicleDetailsDTO.getVehicleDetailsList()) {
            clientVehicleDetailsList.add(mapClientVehicleDetailFromVehicleDetail(vehicleDetails));
        }*/
        List<ClientVehicleDetail> clientVehicleDetailsList = vehicleDetailsDTO.getVehicleDetailsList().stream()
                .map(vehicle -> {
                    try {
                        return mapClientVehicleDetailFromVehicleDetail(vehicle);
                    } catch (VehicleMarketPriceNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        return clientVehicleDetailsList;
    }

    private ClientVehicleDetail mapClientVehicleDetailFromVehicleDetail(VehicleDetails vehicleDetails) throws VehicleMarketPriceNotFoundException {
        ClientVehicleDetail clientVehicleDetail = new ClientVehicleDetail();
        clientVehicleDetail.setId(vehicleDetails.getId());
        clientVehicleDetail.setModelYear(vehicleDetails.getModelYear());
        clientVehicleDetail.setBrandName(vehicleDetails.getBrandName());
        clientVehicleDetail.setModelName(vehicleDetails.getModelName());
        clientVehicleDetail.setTrimType(vehicleDetails.getTrimType());
        clientVehicleDetail.setBodyType(vehicleDetails.getBodyType());
        clientVehicleDetail.setVehiclePrice(vehicleDetails.getVehiclePrice());
        clientVehicleDetail.setMilesOnVehicle(vehicleDetails.getMilesOnVehicle());
        clientVehicleDetail.setLocationOfVehicle(vehicleDetails.getLocationOfVehicle());
        clientVehicleDetail.setSellerName(vehicleDetails.getSellerName());
        clientVehicleDetail.setSellerContactNumber(vehicleDetails.getSellerContactNumber());

        //calculated estimated monthly price
        //price/(5*12) + price*interest_rate/(100*12) Because interest
        // rate has been given for a year and here 5 is indication years
        double monthlyPrice = vehicleDetails.getVehiclePrice() / (5 * 12) + vehicleDetails.getVehiclePrice() * vehicleDetails.getInterestRate() / (100 * 12);
        //$577.31/monthly est.
        clientVehicleDetail.setEstimatedMonthlyPrice("$" + monthlyPrice + "/monthly est.");
        //Calculate amount below or above market price
        //Calculate current market price
        //Market Price (New Vehicle) - (Current year -model year)*market price * 0.5/25-current miles*market price*75/500000
        VehicleMarketPrice dbMarketPriceBasedOnBrandAndModel = vehicleMarketPriceService
                .getVehicleMarketPriceByBrandModel(vehicleDetails.getBrandName(), vehicleDetails.getModelName());
        if(dbMarketPriceBasedOnBrandAndModel==null){
            throw new VehicleMarketPriceNotFoundException("Vehicle Market Price not found for this BrandName & ModelName "+vehicleDetails.getBrandName()+" & "+vehicleDetails.getModelName());
        }
        double currentVehicleMarketPrice = dbMarketPriceBasedOnBrandAndModel.getPrice()
                - (LocalDate.now().getYear() - vehicleDetails.getModelYear())
                * (dbMarketPriceBasedOnBrandAndModel.getPrice() * 0.5 / 25) - (vehicleDetails.getMilesOnVehicle()
                * dbMarketPriceBasedOnBrandAndModel.getPrice() * 0.75 / 500000);
        if (currentVehicleMarketPrice < 0) {
            currentVehicleMarketPrice = 0;
        }
        double marketPriceComparison = currentVehicleMarketPrice - vehicleDetails.getVehiclePrice();
        if (marketPriceComparison > 0) {
            clientVehicleDetail.setAmountBelowMarketPrice("$" + marketPriceComparison + " below market price.");
        } else {
            clientVehicleDetail.setAmountBelowMarketPrice("$" + Math.abs(marketPriceComparison) + " above market price.");
        }
        //deal type determination
        if (marketPriceComparison > 800) {
            clientVehicleDetail.setDealType("Great Deal");
        } else if (marketPriceComparison > 350 && marketPriceComparison <= 800) {
            clientVehicleDetail.setDealType("Good Deal");
        } else if (marketPriceComparison > 100 && marketPriceComparison <= 350) {
            clientVehicleDetail.setDealType("Fair Deal");
        } else {
            clientVehicleDetail.setDealType("Bad Deal");
        }
        return clientVehicleDetail;
    }
}
