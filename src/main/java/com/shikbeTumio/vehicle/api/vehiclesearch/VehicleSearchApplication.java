package com.shikbeTumio.vehicle.api.vehiclesearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class VehicleSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSearchApplication.class, args);
	}
	@Bean
	public RestTemplate getTemplate(){
		return new RestTemplate();
	}
}
