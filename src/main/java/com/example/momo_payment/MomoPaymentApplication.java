package com.example.momo_payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MomoPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomoPaymentApplication.class, args);
	}

}
