package br.com.digital.dguser.converters;

public enum TipoUserEnum { 
	FUNCIONARIO(0,"Funcionario"),  
	Cliente(1,"Cliente" ) ;

	private int id;
	private String descricao;

	 

	private TipoUserEnum(int valor, String descricao) {
		this.id = valor;
		this.descricao = descricao;
	}

	public static String getById(int id) {
		for (TipoUserEnum e : values()) {
			if (e.id == (id))
				return e.getDescricao();
		}
		return null;
	}

	public static int findById(String s) {
		for (TipoUserEnum e : values()) {
			if (e.getDescricao().equals(s) )
				return e.getId();
		}
		return 0;
	}

	 

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	 

}
