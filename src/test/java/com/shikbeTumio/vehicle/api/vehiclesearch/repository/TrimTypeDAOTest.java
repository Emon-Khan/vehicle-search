package com.shikbeTumio.vehicle.api.vehiclesearch.repository;

import com.shikbeTumio.vehicle.api.vehiclesearch.dao.TrimTypeDao;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.TrimType;
import com.shikbeTumio.vehicle.api.vehiclesearch.entity.VehicleMarketPrice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TrimTypeDAOTest {
    MySQLContainer mySQLContainer = new MySQLContainer("mysql:latest")
            .withDatabaseName("vehicle_test_db")
            .withUsername("springstudent")
            .withPassword("springstudent");
    @Autowired
    TrimTypeDao trimTypeDao;

    @Test
    public void TrimTypeDAO_Save_ReturnedSavedTrimType() {
        //Arrange
        TrimType trimType = new TrimType(1,"Em");
        trimType = new TrimType(1,"EX");

        //Act
        TrimType SavedTrimType = trimTypeDao.save(trimType);

        //Assert
        assertThat(SavedTrimType).usingRecursiveComparison().ignoringFields("id").isEqualTo(trimType);
    }

}
