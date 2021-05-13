package br.com.digital.dppessoas.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;

import br.com.digital.dppessoas.converters.FuncaoConverter;
import lombok.Getter;
import lombok.Setter;

@Entity  
@Getter
@Setter
public class Funcionario extends BaseTable { 
 
	private static final long serialVersionUID = 1L;

	@NumberFormat(style = org.springframework.format.annotation.NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal salario;
	
	@Convert(converter = FuncaoConverter.class)
	private String funcao;
	
	@DateTimeFormat(iso = ISO.DATE )
	private LocalDate dataAdmissao;
	
	 
}
