package br.com.digital.gruposfinanceiro.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AgregadoTabValores extends BaseTable implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private CentroCusto centroCusto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private TabelaValores tabelaValores;
	
	public AgregadoTabValores(String name,CentroCusto centroCusto, TabelaValores tabelaValores, BigDecimal percentual) {
		super();
		setName(name);
		this.centroCusto = centroCusto;
		this.tabelaValores = tabelaValores;
		this.percentual = percentual;
	}

	@NumberFormat(style = org.springframework.format.annotation.NumberFormat.Style.DEFAULT, pattern = "##0.00")
	private BigDecimal percentual;
	
	
	
}
