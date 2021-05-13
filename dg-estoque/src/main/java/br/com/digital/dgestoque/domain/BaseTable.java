package br.com.digital.dgestoque.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import io.opencensus.contrib.grpc.util.StatusConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseTable implements Serializable, BaseEntity {

	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;

	private String name;
	private String descricao;
	

	/*public String getAvatarView() {
		byte[] imagem;
		try {
			imagem = this.imagem;
			if (imagem != null)
				imagem = decompressBytes(imagem);
			avatarView = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imagem);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return avatarView;
	}

	public byte[] decompressBytes(byte[] data) {

		Inflater inflater = new Inflater();

		inflater.setInput(data);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];

		try {
			while (!inflater.finished()) {

				int count = inflater.inflate(buffer);

				outputStream.write(buffer, 0, count);

			}

			outputStream.close();

		} catch (IOException ioe) {

		} catch (DataFormatException e) {

		}

		return outputStream.toByteArray();

	}
*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseTable other = (BaseTable) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	 

}
