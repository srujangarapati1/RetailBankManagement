package com.boot.spring.RetailBanking.Login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.boot.spring.RetailBanking.Login.Bean.Login;
import com.boot.spring.RetailBanking.Login.Repository.LoginRepo;
import com.boot.spring.RetailBanking.Login.Service.LoginSerivice;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin
public class LoginApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LoginApplication.class, args);
		LoginSerivice ls = context.getBean(LoginSerivice.class);
		LoginRepo repo = ls.getRepo();
		repo.save(new Login("gvvsrujan@gmail.com","srujan123", "Accountant"));
		
	}

}
