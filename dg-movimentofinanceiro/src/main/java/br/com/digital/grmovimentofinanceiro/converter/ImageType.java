package br.com.digital.grmovimentofinanceiro.converter;

public enum ImageType {
	JPG("jpg"), PNG("png"), JPEG("jpeg");

	 
	

	private String value;
	ImageType(String string) {
		value=string;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
