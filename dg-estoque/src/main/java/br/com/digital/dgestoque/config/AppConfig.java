package br.com.digital.dgestoque.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.digital.dgestoque.services.utils.FilesService;
import br.com.digital.dgestoque.services.utils.UtilFile;

@Configuration
public class AppConfig {

 

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	 
	@Bean
	public FilesService filesService() {
		return new UtilFile();
	}
 
}
