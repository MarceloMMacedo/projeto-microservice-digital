package br.com.digital.dppessoas.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NatureaPessoaConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return NaturezaPessoaEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  NaturezaPessoaEnum.getById(dbData);
	}

	 
}
