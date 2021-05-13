package br.com.digital.grmovimentofinanceiro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import br.com.digital.grmovimentofinanceiro.converter.FormaPagamentoConverter;
import br.com.digital.grmovimentofinanceiro.converter.StatusConverter;
import br.com.digital.grmovimentofinanceiro.converter.TipoMovimentoConverter;
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

	private Long banco;

	private Long idbancopagador;

	private Long idfuncionario;

	@Convert(converter = FormaPagamentoConverter.class)
	private String formapagamento;

	@Convert(converter = StatusConverter.class)
	private String status;

	private Long historico;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal valor;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private LocalDate dataMovimento;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private LocalDate dataVencimento;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private LocalDate dataQuitacao;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, pattern = "dd/MM/yyyy")
	private LocalDate dataleitura;

	@NumberFormat(style = Style.DEFAULT, pattern = "0")
	private int parcela;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double jurus;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double multa;

	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double desconto;

	@Convert(converter = TipoMovimentoConverter.class)
	private String tipomovimento;

	@Transient
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double total;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDate instantCreation;

	@Column(name = "imagem")
	private String imagem;

	@Transient
	private String imageView;

	/*
	 * public String getAvatarView() { byte[] imagem; try { imagem = this.imagem; if
	 * (imagem != null) imagem = decompressBytes(imagem); avatarView =
	 * "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imagem); }
	 * catch (Exception e) { System.out.println(e.getMessage()); } return
	 * avatarView; }
	 * 
	 * public byte[] decompressBytes(byte[] data) {
	 * 
	 * Inflater inflater = new Inflater();
	 * 
	 * inflater.setInput(data);
	 * 
	 * ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
	 * byte[] buffer = new byte[1024];
	 * 
	 * try { while (!inflater.finished()) {
	 * 
	 * int count = inflater.inflate(buffer);
	 * 
	 * outputStream.write(buffer, 0, count);
	 * 
	 * }
	 * 
	 * outputStream.close();
	 * 
	 * } catch (IOException ioe) {
	 * 
	 * } catch (DataFormatException e) {
	 * 
	 * }
	 * 
	 * return outputStream.toByteArray();
	 * 
	 * }
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
