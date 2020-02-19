package com.boot.spring.RetailBanking.RetailBanking_CustomerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RetailBankingCustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingCustomerServiceApplication.class, args);
	}
}
