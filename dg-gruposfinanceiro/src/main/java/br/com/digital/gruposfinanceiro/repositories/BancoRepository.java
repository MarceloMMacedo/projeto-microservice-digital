package br.com.digital.gruposfinanceiro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digital.gruposfinanceiro.domain.Banco;
import org.springframework.stereotype.Repository;

@Repository
public interface BancoRepository extends JpaRepository<Banco, Long> {

}
