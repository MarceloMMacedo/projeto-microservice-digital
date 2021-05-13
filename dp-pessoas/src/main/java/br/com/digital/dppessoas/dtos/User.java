package br.com.digital.dppessoas.dtos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
 
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
 
	private Long id;
	private String name;	 
	private String tipouser; 
	private String email; 
	private String status;
	private Role roles ;	
	private Long idrole;
	private Long funcionario;	
	private Long cliente;

	
 
	

	public User() {
	}
	 
	public User(Long id, String name, String email, String password,Long idrole) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.idrole = idrole;
	}

	public User(String name, String email, Long idrole) {
		super();
		this.name = name;
		this.email = email;
		this.idrole = idrole;
	}
 
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
