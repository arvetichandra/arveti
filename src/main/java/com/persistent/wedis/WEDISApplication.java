package com.persistent.wedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WEDISApplication {

	public static void main(String[] args) {
		System.out.println("hello");
		SpringApplication.run(WEDISApplication.class, args);
	}

}
