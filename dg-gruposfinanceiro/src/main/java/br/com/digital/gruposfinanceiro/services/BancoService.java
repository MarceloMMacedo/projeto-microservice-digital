package br.com.digital.gruposfinanceiro.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digital.gruposfinanceiro.domain.Banco;
import br.com.digital.gruposfinanceiro.repositories.BancoRepository;

public class BancoService extends ServiceBase<Banco> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private BancoRepository repo;

	@Override
	public JpaRepository<Banco, Long> repo() {
		return repo;
	}

}
