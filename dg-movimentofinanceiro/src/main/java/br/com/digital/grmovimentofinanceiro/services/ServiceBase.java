package br.com.digital.grmovimentofinanceiro.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.FileList;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;

import br.com.digital.grmovimentofinanceiro.config.DriveQuickstart;
import br.com.digital.grmovimentofinanceiro.domain.BaseEntity;
import br.com.digital.grmovimentofinanceiro.dtos.BaseDto;
import br.com.digital.grmovimentofinanceiro.dtos.Role;
import br.com.digital.grmovimentofinanceiro.faingbases.UserFeingClient;
import br.com.digital.grmovimentofinanceiro.services.exceptions.DataIntegrityException;
import br.com.digital.grmovimentofinanceiro.services.exceptions.FileException;

import com.google.cloud.storage.StorageOptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@NoArgsConstructor
public class ServiceBase<T extends BaseEntity> {

	@Autowired
	DriveQuickstart googleDriveManager;

	@Autowired
	ResourceLoader resourceLoader;
	
	@Autowired
	private UserFeingClient userFeingClient ;

	// firebase
	@Value("${spring.cloud.gcp.credentials.location}")
	private String credencial;

	@Value("${spring.cloud.gcp.config_projectId}")
	private String projectId;

	@Value("${spring.cloud.gcp.bucketName}")
	private String bucketName;

	public JpaRepository<T, Long> repo() {
		return null;
	}

	T obj;

	public Class<T> getClasse() {
		Class<T> classe = null;
		try {
			Class<T> class1 = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			classe = class1;
		} catch (Exception e) {
		}
		return classe;
	}

	/**
	 * @param id
	 * @return objeto por id
	 */

	public T find(Long id) {
		try {
			obj = (T) repo().findById(id).get();
			log.info("localizar objeto:" + getClasse().getSimpleName() + " por id");
		} catch (Exception e) {
			log.error("erro localizar objeto:" + getClasse().getSimpleName() + " por id, descrição:" + e.getMessage());
			return null;
		}
		return obj;
	}

	/**
	 * @param obj
	 * @return insere objeto no banco de dados
	 */

	public T insert(T obj) {

		// obj.setEmpresa(user.getEmpresa());
		try {

			repo().save(obj);
			log.info("inserindo  objeto:" + getClasse().getSimpleName() + " - " + obj.toString());
		} catch (Exception e) {
			log.error("erro de inserção objeto:" + getClasse().getSimpleName() + ", descrição:" + e.getMessage());
		}

		return obj;
	}

	/**
	 * @param obj
	 * @return objeto atualizado
	 */

	public T update(T obj, Long id) {

		try {
			repo().save(obj);
			log.info("atualização  objeto:" + getClasse().getSimpleName() + " com sucesso, id:" + "" + obj.getId());
		} catch (Exception e) {
			log.error("erro de inserção objeto:" + getClasse().getSimpleName() + ", descrição:" + e.getMessage());
		}
		return obj;
	}

	/**
	 * delete from id
	 * 
	 * @param id
	 */

	public void delete(Long id) {

		// find(id);
		try {
			repo().delete(repo().findById(id).get());
			log.info("deleção do objeto:" + getClasse().getSimpleName() + " com sucesso, id:" + "" + id);

		} catch (DataIntegrityViolationException e) {
			log.error("Não é possível excluir porque há pedidos relacionados");
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}

	}

	/**
	 * @return lista de itens
	 */

	public List<T> findAll() {
		List<T> find = (List<T>) repo().findAll();
		log.info("lista de itens da classe:" + getClasse().getSimpleName() + " gerado com sucesso");
		return find;
	}

	/**
	 * @param email
	 * @return objeto por email
	 */

	public T findByEmail(String email) {
		return null;
	}

	/**
	 * @param name
	 * @return lista de objetos pelo name
	 */
	public Page<BaseDto> findPage(String name,Pageable pageable) {
		return null;
	}

	public List<BaseDto> findBaseAll() {
		return null;
	}

	public List<T> findAllName(String name) {

		return null;
	}

	public Double findBySumValorAndStatus(String status) {
		return null;
	}

	public byte[] ViewPdf() {
		// TODO Auto-generated method stub
		return null;
	}

	// gdrive

	// FileManager.java
	public String uploadFile(MultipartFile file, String fileName) throws GeneralSecurityException, IOException {

		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		Drive service = new Drive.Builder(HTTP_TRANSPORT, DriveQuickstart.JSON_FACTORY,
				DriveQuickstart.getCredentials(HTTP_TRANSPORT)).setApplicationName(DriveQuickstart.APPLICATION_NAME)
						.build();

		// Print the names and IDs for up to 10 files.
		FileList result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();
		List<com.google.api.services.drive.model.File> files = result.getFiles();
		if (files == null || files.isEmpty()) {
			System.out.println("No files found.");
		} else {
			System.out.println("Files:");
			for (com.google.api.services.drive.model.File file2 : files) {
				System.out.printf("%s (%s)\n", file.getName(), file2.getId());
			}
		}
		return null;
	}

	public void uploadFile(MultipartFile file, Long id) {
		String fileName = null;
		obj = find(id);
		String t = fileName;
		String s = obj.getImagem();
		File file1 = null;
		try {
			file1 = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "credencial.json");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			fileName = file.getOriginalFilename(); // to get original file name
			fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName)); // to generated random string
			BlobId blobId = BlobId.of(bucketName, s);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();
			Credentials credentials = null;

			credentials = GoogleCredentials.fromStream(new FileInputStream(file1));

			Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();

			t = storage.get(BlobId.of(bucketName, s)).getName();
			System.out.println(s);
			System.out.println(t);
			if (s.equals(t)) {

				deleteimage(t);
				fileName = t;
			} else
				obj.setImagem(fileName);
		} catch (Exception e) {
			obj.setImagem(fileName);
		}
		update(obj, id);

		upload(file, fileName);

	}

	private String uploadFile(File file, String fileName, String media) throws IOException {

		File file1 = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "credencial.json");

		String content = new String(Files.readAllBytes(file1.toPath()));
		System.out.println(credencial);

		BlobId blobId = BlobId.of(bucketName, fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(media).build();
		Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(file1));
		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		storage.create(blobInfo, Files.readAllBytes(file.toPath()));
		System.out.println(storage.get(BlobId.of(bucketName, fileName)));
		return downloadFile(fileName);// String.format("https://firebasestorage.googleapis.com/v0/b/digital-servicos.appspot.como/%s?alt=media",
										// URLEncoder.encode(fileName, StandardCharsets.UTF_8));
	}

	private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
		File tempFile = new File(fileName);
		try (FileOutputStream fos = new FileOutputStream(tempFile)) {
			fos.write(multipartFile.getBytes());
			fos.close();
		}
		return tempFile;
	}

	private String getExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	public String downloadFile(String fileName) {

		Resource res = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "/tmp");
		File ins = null;
		try {
			ins = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "credencial.json");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ="cliente5ef7668b24f13062db255a24.png";

		URL signedUrl = null;

		InputStream in = null;
		try {
			in = new FileInputStream(ins);
		} catch (FileNotFoundException e) {
			return "";
		}

		Credentials credential = null;
		try {
			credential = GoogleCredentials.fromStream(in);
		} catch (IOException e) {
			return "";
		}

		Storage storage = StorageOptions.newBuilder().setCredentials(credential).setProjectId(projectId).build()
				.getService();

		try {

			signedUrl = storage.signUrl(BlobInfo.newBuilder(bucketName, fileName).build(), 1, TimeUnit.DAYS,
					SignUrlOption.signWith(ServiceAccountCredentials.fromStream(new FileInputStream(ins))));
		} catch (Exception e) {
			return "";
		}

		return signedUrl.toString();
	}

	public void deleteimage(String filename) {
		File file1 = null;
		try {
			file1 = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "credencial.json");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		BlobId blobId = BlobId.of(bucketName, filename); 
		Credentials credentials = null;
		try {
			credentials = GoogleCredentials.fromStream(new FileInputStream(file1));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService(); 
		try {

			storage.delete(bucketName, filename);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void upload(MultipartFile multipartFile, String fileName) {
		Resource res = resourceLoader.getResource(ResourceUtils.CLASSPATH_URL_PREFIX + "/tmp");
		File file = null;
		try {
			String media = multipartFile.getContentType(); // values for file name.

			  file = this.convertToFile(multipartFile, fileName); // to convert multipartFile to File
			String TEMP_URL = this.uploadFile(file, fileName, media); // to get uploaded file link
			file.delete(); // to delete the copy of uploaded file stored in the project folder 
		} catch (Exception e) {
			try{file.delete(); }catch (Exception e1) { }
			throw new FileException("Erro gravar arquivo: " + e.getMessage());
			// return sendResponse("500", e, "Unsuccessfully Uploaded!");
		}

	}
	public List<Role> rules(){
		List<Role>  rules =userFeingClient.rules().getBody();
		return rules;	
	}
}
