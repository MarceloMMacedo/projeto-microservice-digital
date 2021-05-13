package br.com.digital.grmovimentofinanceiro;

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
public class DgMovimentofinanceiroApplication implements CommandLineRunner {

	@Autowired
	private IntTable intTable;

	public static void main(String[] args) {
		SpringApplication.run(DgMovimentofinanceiroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {

			intTable.intTable();

		} catch (Exception e) {
			System.out.println(e);
		}
		/*
		 * empresa.setName("Paula Andréa Gomes Macedo ME");
		 * empresa.setInstagran("@Digital"); Empresa.saveEmpresa(empresa);
		 */
	}
}
