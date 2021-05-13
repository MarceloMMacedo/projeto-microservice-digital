package br.com.digital.gruposfinanceiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class DgGruposfinanceiroApplication implements CommandLineRunner {

	@Autowired
	private IntTable intTable;

	public static void main(String[] args) {
		SpringApplication.run(DgGruposfinanceiroApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		 
				intTable.intTable();
	}
}
