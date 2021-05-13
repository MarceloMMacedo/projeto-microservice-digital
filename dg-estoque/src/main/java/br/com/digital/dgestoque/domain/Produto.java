package br.com.digital.dgestoque.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import br.com.digital.dgestoque.converters.ConverterUnidadeProduto;
import br.com.digital.dgestoque.converters.SimNaoConverter;
import lombok.Data;

@Entity
@Data
public class Produto implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	private String imagem;
	 
	@Convert(converter = GrupoFinanceiroTipoConverter.class)
	private String tipoProduto;

	@JoinColumn 
	private ProdutoCategoria categoriaProduto;

	@Convert(converter = ConverterUnidadeProduto.class)
	private String unidade;

	@Convert(converter = SimNaoConverter.class)
	private String ativo;

	private double medida;

	private double saldoCompra;

	private String localizacao;
}
