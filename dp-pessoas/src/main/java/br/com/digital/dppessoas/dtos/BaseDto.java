package br.com.digital.dppessoas.dtos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import br.com.digital.dppessoas.converters.ImageType;
import br.com.digital.dppessoas.domain.BaseTable;
import lombok.Data;

@Data
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String imagem;

	private String name;

	public BaseDto(BaseTable obj,String avatarVie) {
		super();
		this.id = obj.getId();
	 
		this.imagem =  avatarVie;
		name = obj.getName();
	}

	/**
	 * Redimensiona a imagem.
	 *
	 * @return array de bytes com imagem redimensionada
	 */
	private   byte[] resizeImage(byte[] imageByte, int width, int height) throws IOException {
		try (InputStream inputImage = new ByteArrayInputStream(imageByte);) {
			BufferedImage image = ImageIO.read(inputImage);
			BufferedImage imgResized = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			imgResized.getGraphics().drawImage(image, 0, 0, width, height, null);
			return imageToByte(imgResized);
		}
	}

	/**
	 * Converte BufferedImage para array de bytes no formato JPG.
	 *
	 */
	private   byte[] imageToByte(BufferedImage img) throws IOException {
		return imageToByte(img, ImageType.JPG);
	}

	/**
	 * Converte BufferedImage para array de bytes.
	 *
	 */
	private   byte[] imageToByte(BufferedImage img, ImageType tipoImagem) throws IOException {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			ImageIO.write(img, tipoImagem.getValue(), baos);
			baos.flush();
			return baos.toByteArray();
		}
	}

	 
}
