package com.store.ezbuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class EzbuyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EzbuyApplication.class, args);
	}

}
