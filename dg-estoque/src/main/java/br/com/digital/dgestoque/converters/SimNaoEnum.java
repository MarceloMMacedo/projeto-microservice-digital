package br.com.digital.dgestoque.converters;

public enum SimNaoEnum {
	SIM("Sim", 0),NAO("Não", 1) ;
	private final String nome;
	private final int id;

	private SimNaoEnum(String nome, int id) {
		this.nome = nome;
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public int getId() {
		return id;
	}

	public static String getById(int id) {
		for (SimNaoEnum e : values()) {
			if (e.getId() == (id))
				return e.getNome();
		}
		return null;
	}
	public static int findById(String s) {
		for (SimNaoEnum e : values()) {
			if (e.getNome().equals(s))
				return e.getId();
		}
		return -1;
	} 
}
