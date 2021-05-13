package br.com.digital.dguser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.digital.dguser.repositories.UserRepository;

@EnableEurekaClient
@SpringBootApplication
public class DgUserApplication implements CommandLineRunner {

	 

	@Autowired
	private IntTable intTable;
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(DgUserApplication.class, args);
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
	}
}