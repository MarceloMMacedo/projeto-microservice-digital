package br.com.digital.gruposfinanceiro.dtos;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String roleName;
	private String descricao;

	public Role() {
	}

	public Role(Long id, String roleName) {
		super();
		this.id = id;
		this.roleName = roleName;
	}

	public Role(Long id, String roleName, String descricao) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.descricao = descricao;
	}

	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
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
		Role other = (Role) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

	 
}
