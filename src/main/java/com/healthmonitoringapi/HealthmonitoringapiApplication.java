package com.healthmonitoringapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HealthmonitoringapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthmonitoringapiApplication.class, args);
	}

}
