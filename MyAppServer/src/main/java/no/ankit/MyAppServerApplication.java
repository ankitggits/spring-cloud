package no.ankit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyAppServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyAppServerApplication.class, args);
	}
}
