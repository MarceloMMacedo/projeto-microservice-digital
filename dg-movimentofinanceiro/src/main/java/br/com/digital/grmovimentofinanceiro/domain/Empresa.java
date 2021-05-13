package br.com.digital.grmovimentofinanceiro.domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.springframework.util.ResourceUtils;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.SignUrlOption;
import com.google.cloud.storage.StorageOptions;

import lombok.Data;

@Data
public class Empresa {

	private String name;
	private String descricao;
	private String cnpj;
	private String site;
	private String instagram;

	private String telefone;

	private String streinstagramet;
	private String zipCode;
	private String bairro;
	private String city;
	private String nro;
	private String complement;
	private String state;

	private String imagem;

	private String imagemview;
	private String email;

	public static Empresa getEmpresa() {

		File file =null;

		try {
			file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "app.properties");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Empresa empresa = new Empresa();

		Properties properties = new Properties();

		// Setamos o arquivo que vai ser lido
		FileInputStream fis;

		try {
			fis = new FileInputStream(file);
			properties.load(fis);
			Class classefonte = Empresa.class;

			Field fldfonte = null;
			Field fldDestino = null;
			Field[] fs = classefonte.getDeclaredFields();
			for (Field f : fs) {
				String string = f.getName();
				fldfonte = classefonte.getDeclaredField(string);
				fldfonte.setAccessible(true);
				String value = properties.getProperty(string);
				fldfonte.set(empresa, value);

			}

		} catch (IOException e) {

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//get avatar
		URL signedUrl = null;

		InputStream in = null;
	

		try {
			file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "credencial.json");
			in = new FileInputStream(file);
		} catch (FileNotFoundException e) {

		}

		Credentials credential = null;
		try {
			credential = GoogleCredentials.fromStream(in);
		} catch (IOException e) {

		}
		

		try {
			
			String credencial = file.getAbsolutePath();

			String projectId = "digital-solucoes";

			String bucketName = "digital-servicos.appspot.com";
			Storage storage = StorageOptions.newBuilder().setCredentials(credential).setProjectId(projectId).build()
					.getService();
			signedUrl = storage.signUrl(BlobInfo.newBuilder(bucketName, empresa.getImagem()).build(), 1, TimeUnit.DAYS,
					SignUrlOption.signWith(ServiceAccountCredentials.fromStream(new FileInputStream(file))));
			System.out.println(signedUrl.toString());
			
			empresa.setImagemview(signedUrl.toString());
		} catch (Exception e) {
		}
		return empresa;

	}

	public static void saveEmpresa(Empresa empresa) {
		File file = null;
		try {
			file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "app.properties");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		Properties properties = new Properties();

		Class classefonte = Empresa.class;
		Field fldfonte = null;
		Field[] fs = classefonte.getDeclaredFields();

		for (Field f : fs) {
			String string = f.getName();
			try {
				fldfonte = classefonte.getDeclaredField(string);

				fldfonte.setAccessible(true);
				String value = (String) fldfonte.get(empresa);
				properties.setProperty(string, value);

			} catch (Exception e) {

			}
		}
		try {
			// Criamos um objeto FileOutputStream
			FileOutputStream fos = new FileOutputStream(file);
			// grava os dados no arquivo
			properties.store(fos, "FILE EMPRESA PROPERTIES:");
			// fecha o arquivo
			fos.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
