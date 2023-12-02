package com.shikbeTumio.vehicle.api.vehiclesearch.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;

@Entity
@Table(name="market_price")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleMarketPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int modelYear;
    private String brandName;
    private String modelName;
    private double price;
}
