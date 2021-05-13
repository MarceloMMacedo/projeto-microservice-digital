package br.com.digital.gruposfinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.gruposfinanceiro.domain.TabelaValores;

@Repository
public interface TabelaValoresRepository extends JpaRepository<TabelaValores, Long> {

}
