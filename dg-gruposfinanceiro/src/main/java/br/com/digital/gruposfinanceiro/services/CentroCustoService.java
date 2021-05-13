package br.com.digital.gruposfinanceiro.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digital.gruposfinanceiro.domain.CentroCusto;
import br.com.digital.gruposfinanceiro.repositories.CentroCustoRepository;

public class CentroCustoService extends ServiceBase<CentroCusto> implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private CentroCustoRepository repo;

	@Override
	public JpaRepository<CentroCusto, Long> repo() {
		return repo;
	}

}
