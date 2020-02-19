package com.boot.spring.RetailBanking.RetailBanking_EurekaServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RetailBankingEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingEurekaServerApplication.class, args);
	}
}
