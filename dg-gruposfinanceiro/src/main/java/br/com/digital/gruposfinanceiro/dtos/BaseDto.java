package br.com.digital.gruposfinanceiro.dtos;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.imageio.ImageIO;

import br.com.digital.gruposfinanceiro.converters.ImageType;
import br.com.digital.gruposfinanceiro.domain.BaseTable;
import lombok.Data;

@Data
public class BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
 
	private String name;

	 

 

	 
}
