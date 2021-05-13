package br.com.digital.dppessoas.domain;

interface BaseImg {

	Long getId();

	void setId(Long id);

	byte[] getImagem();

	void setImagem(byte[] imagem);

	Long getIdmaster();

	void setIdmaster(Long idmaster);

}
