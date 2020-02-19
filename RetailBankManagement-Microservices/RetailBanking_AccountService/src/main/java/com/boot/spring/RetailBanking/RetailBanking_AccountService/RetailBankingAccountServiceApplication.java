package com.boot.spring.RetailBanking.RetailBanking_AccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RetailBankingAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingAccountServiceApplication.class, args);
	}
}
