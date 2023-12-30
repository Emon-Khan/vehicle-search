package com.shikbeTumio.vehicle.api.vehiclesearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableDiscoveryClient
public class VehicleSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleSearchApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate(){
		return new RestTemplate();
	}
}
