package br.com.digital.grmovimentofinanceiro.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
  

@Converter
public class TipoFornecedorConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return TipoFornecedorEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  TipoFornecedorEnum.getById(dbData);
	}

	 
}
