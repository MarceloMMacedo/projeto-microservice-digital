package br.com.digital.dguser.converters;


import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
  

@Converter
public class TipoUserConverter implements
AttributeConverter<String, Integer>{

	@Override
	public Integer convertToDatabaseColumn(String attribute) { 
		return TipoUserEnum.findById(attribute);
	}

	@Override
	public String convertToEntityAttribute(Integer dbData) { 
		return  TipoUserEnum.getById(dbData);
	}

	 
}
