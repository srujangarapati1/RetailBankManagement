package com.boot.spring.RetailBanking.RetailBanking_TransactionService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient

public class RetailBankingTransactionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingTransactionServiceApplication.class, args);
	}
}
