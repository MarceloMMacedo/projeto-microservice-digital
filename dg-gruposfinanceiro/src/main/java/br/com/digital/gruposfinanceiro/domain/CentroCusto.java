package br.com.digital.gruposfinanceiro.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CentroCusto extends BaseTable {

	private static final long serialVersionUID = 1L;

	@NumberFormat(style = org.springframework.format.annotation.NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal saldo;
	
 
	@OneToMany(mappedBy = "centroCusto",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<AgregadoTabValores> agregadoTabValores;
	
	

	/*
	 * @NumberFormat(style =
	 * org.springframework.format.annotation.NumberFormat.Style.CURRENCY, pattern =
	 * "#,##0.00") private BigDecimal saldoPagar;
	 * 
	 * @NumberFormat(style =
	 * org.springframework.format.annotation.NumberFormat.Style.CURRENCY, pattern =
	 * "#,##0.00") private BigDecimal saldoReceber;
	 */
}
