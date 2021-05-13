package br.com.digital.dppessoas.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ResourceUtils;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import br.com.digital.dppessoas.domain.Empresa;
import br.com.digital.dppessoas.dtos.PessoaDTO;
import br.com.digital.dppessoas.services.exceptions.FileException;
import br.com.digital.dppessoas.services.utils.FilesService;
import br.com.digital.dppessoas.services.utils.UtilFile;

@Configuration
public class AppConfig {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${spring.cloud.gcp.credentials.location}")
	private String credencial;

	@Value("${spring.cloud.gcp.config_projectId}")
	private String projectId;

	@Value("${spring.cloud.gcp.bucketName}")
	private String bucketName;

	@Value("${spring.cloud.gcp.config_projectId}")
	private String objectName;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public Empresa empresa() {
		 
		return Empresa.getEmpresa();
	}

	@Bean
	public FilesService filesService() {
		return new UtilFile();
	}

// @Autowired(required=true)
//	private GenTable genegationTable;
// 

	public Storage storage() {

		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, objectName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		try {
			storage.create(blobInfo, Files.readAllBytes(Paths.get(credencial)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return storage;
	}

}
