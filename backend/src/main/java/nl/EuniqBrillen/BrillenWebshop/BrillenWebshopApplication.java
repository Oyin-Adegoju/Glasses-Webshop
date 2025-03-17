package nl.EuniqBrillen.BrillenWebshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "nl.EuniqBrillen.BrillenWebshop.entity")
public class BrillenWebshopApplication {
	public static void main(String[] args) {SpringApplication.run(BrillenWebshopApplication.class, args);
	}

}
