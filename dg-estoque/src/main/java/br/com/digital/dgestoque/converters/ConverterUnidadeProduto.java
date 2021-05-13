package br.com.digital.dgestoque.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ConverterUnidadeProduto  implements
AttributeConverter<String, Integer> {

	@Override
	public Integer convertToDatabaseColumn(String arg0) { 
		return EnumUnidade.findById(arg0);
	}

	@Override
	public String convertToEntityAttribute(Integer arg0) { 
		return EnumUnidade.getById(arg0);
	}

}
