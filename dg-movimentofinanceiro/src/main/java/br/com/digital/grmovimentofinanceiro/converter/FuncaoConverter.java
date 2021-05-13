package br.com.digital.grmovimentofinanceiro.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FuncaoConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return FuncaoEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  FuncaoEnum.getById(dbData);
	}

	 
}
