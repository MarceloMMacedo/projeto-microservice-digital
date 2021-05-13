package br.com.digital.gruposfinanceiro.services.utils;

public class CnpjJoson {
	private String key;
	private String value;

	public CnpjJoson() {
		super();
	}

	public CnpjJoson(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
