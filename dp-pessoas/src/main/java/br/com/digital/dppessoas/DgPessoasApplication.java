package br.com.digital.dppessoas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import br.com.digital.dppessoas.domain.Empresa;
import br.com.digital.dppessoas.repositories.ClienteRepository;

@EnableCircuitBreaker
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class DgPessoasApplication implements CommandLineRunner {

	@Autowired
	private IntTable intTable;

	@Autowired
	private ClienteRepository userRepository;

	@Autowired
	private Empresa empresa;

	public static void main(String[] args) {
		SpringApplication.run(DgPessoasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			int i = userRepository.findAll().size();
			if (userRepository.findAll().size() == 0 || userRepository.findAll() == null) {

				intTable.intTable();
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		/*empresa.setName("Paula Andréa Gomes Macedo ME");
		empresa.setInstagran("@Digital");
		Empresa.saveEmpresa(empresa);
		*/
	}

}
