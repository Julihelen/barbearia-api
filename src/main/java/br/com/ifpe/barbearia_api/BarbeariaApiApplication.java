package br.com.ifpe.barbearia_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BarbeariaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BarbeariaApiApplication.class, args);
	}

}
