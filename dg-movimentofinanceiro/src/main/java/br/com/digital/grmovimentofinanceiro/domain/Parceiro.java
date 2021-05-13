package br.com.digital.grmovimentofinanceiro.domain;

import java.math.BigDecimal;

import javax.persistence.Convert;
import javax.persistence.Entity;

import org.springframework.format.annotation.NumberFormat;

import br.com.digital.grmovimentofinanceiro.converter.NatureaPessoaConverter;
import br.com.digital.grmovimentofinanceiro.converter.TipoPessoaConverter;
import lombok.Getter;
import lombok.Setter;

@Entity  
@Getter
@Setter
public class Parceiro extends BaseTable { 

	private static final long serialVersionUID = 1L;
	
	@Convert(converter = NatureaPessoaConverter.class)
	private String natureza;
	private String code;
	private String data_situacao;
	@Convert(converter = TipoPessoaConverter.class)
	private String tipo;
	private String porte;
	private String natureza_juridica;
	private String capital_social;
	private String atividade_principal;
	private String nameFantasia;	
	@NumberFormat(style = org.springframework.format.annotation.NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal limitecredito;
	
 
}
