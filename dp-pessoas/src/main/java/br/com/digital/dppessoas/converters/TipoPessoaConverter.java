package br.com.digital.dppessoas.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
  

@Converter
public class TipoPessoaConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return TipoPessoaEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  TipoPessoaEnum.getById(dbData);
	}

	 
}
