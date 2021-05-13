package br.com.digital.dgestoque.domain;

public interface BaseEntity {

	Long getId();

	/*
	 * String getName();
	 * 
	 * String getAvatar();
	 * 
	 * String getAvatarView();
	 * 
	 * String getEmail();
	 */
	void setId(Long id);

	void setImagem(String imagem);
	
	String getImagem();
}
