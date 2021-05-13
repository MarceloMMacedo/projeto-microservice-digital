package br.com.digital.grmovimentofinanceiro.converter;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TipoMovimentoConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return TipoMovimentoEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  TipoMovimentoEnum.getById(dbData);
	}

	 
}
