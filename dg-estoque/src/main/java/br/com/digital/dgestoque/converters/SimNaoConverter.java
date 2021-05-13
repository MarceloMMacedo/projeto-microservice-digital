package br.com.digital.dgestoque.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
 

@Converter
public class SimNaoConverter  implements
AttributeConverter<String, Integer> {

	@Override
	public Integer convertToDatabaseColumn(String arg0) { 
		return SimNaoEnum.findById(arg0);
	}

	@Override
	public String convertToEntityAttribute(Integer arg0) { 
		try {
			return SimNaoEnum.getById(arg0);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

}
