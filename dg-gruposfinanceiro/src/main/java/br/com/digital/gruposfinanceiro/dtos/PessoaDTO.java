package br.com.digital.gruposfinanceiro.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.Data;

@Data
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	private String descricao;
	private String cnpj;
	private String cpf;
	private String rg;
	private String telefone;
	private String street;
	private String zipCode;
	private String bairro;
	private String city;
	private String nro;
	private String complement;
	private String state;
	private String status;
	private Long idempresa;
	@DateTimeFormat(iso = ISO.DATE )
	private LocalDate instantCreation;
	private String imagem;

	// funcionario
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal salario;
	private String funcao; 
	@DateTimeFormat(iso = ISO.DATE )
	private LocalDate dataAdmissao;
	
	//cliente-fornecedores-empresa 
	private String natureza;
	private String code;
	private String data_situacao; 
	private String tipo;
	private String porte;
	private String natureza_juridica;
	private String capital_social;
	private String atividade_principal;
	private String nameFantasia;	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal limitecredito;
	
	private Long iduser;

	// User
	private User user = new User();

}
