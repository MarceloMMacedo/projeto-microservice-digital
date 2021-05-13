package br.com.digital.gruposfinanceiro.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.digital.gruposfinanceiro.converters.FuncaoConverter;

import org.springframework.format.annotation.NumberFormat;

import lombok.Getter;
import lombok.Setter;

@Entity  
@Getter
@Setter
public class Banco extends BaseTable { 
 
	private static final long serialVersionUID = 1L;

	@NumberFormat(style = org.springframework.format.annotation.NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal saldo;
	
	private String agencia;
	private String conta;
	private String banco;
	
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "banco")
 	private List<MovimentoBanco> movimentoBancos;
	 
}
