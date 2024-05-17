package dev.emrx.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class AuthJwtServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthJwtServiceApplication.class, args);
	}

}
