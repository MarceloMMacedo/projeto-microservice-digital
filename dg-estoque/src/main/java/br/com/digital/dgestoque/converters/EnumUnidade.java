package br.com.digital.dgestoque.converters;

public enum EnumUnidade {
	UNIDADE ("Unidade", 0),
	KILO("Kilo",1),
	REFIL("Refil",2),
	CARTUCHO("Cartucho",3) ;


	private final String nome;
	
	private final int id;

	private EnumUnidade(String nome, int id) {
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
		for (EnumUnidade e : values()) {
			if (e.getId() == (id))
				return e.getNome();
		}
		return null;
	}
	public static int findById(String s) {
		for (EnumUnidade e : values()) {
			if (e.getNome().equals(s))
				return e.getId();
		}
		return -1;
	}  
	
}
