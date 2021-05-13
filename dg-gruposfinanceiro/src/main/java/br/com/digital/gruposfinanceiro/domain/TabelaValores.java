package br.com.digital.gruposfinanceiro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class TabelaValores extends BaseTable implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@OneToMany(mappedBy = "tabelaValores")
	private List<AgregadoTabValores> agregadoTabValores;
	
	@NumberFormat(style = org.springframework.format.annotation.NumberFormat.Style.DEFAULT, pattern = "##0.00")
	private BigDecimal percMaxDesconto;

}
