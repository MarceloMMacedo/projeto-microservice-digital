package br.com.digital.gruposfinanceiro.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
 
@Getter
@Setter
@Entity
public class MovimentoBanco extends BaseTable implements Serializable, BaseEntity {
 
	private static final long serialVersionUID = 1L;

	@NumberFormat(style = org.springframework.format.annotation.NumberFormat.Style.CURRENCY, pattern = "#,##0.00")
	private BigDecimal valor;
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDate datamovimento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@JsonIgnore
	private Banco banco;

}
