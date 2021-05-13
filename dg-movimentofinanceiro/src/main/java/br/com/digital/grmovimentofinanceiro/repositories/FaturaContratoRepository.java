package br.com.digital.grmovimentofinanceiro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.digital.grmovimentofinanceiro.domain.FaturaContrato;

@Repository
public interface FaturaContratoRepository extends JpaRepository<FaturaContrato, Long> {

	Page<FaturaContrato> findByNameIgnoreCaseContaining(String name, Pageable pageable);
	/*
	 * @Query("SELECT  new br.com.digital.grmovimentofinanceiro.dtos.BaseDto(o) FROM FaturaSaida o "
	 * ) List<BaseDto> findPage(Pageable pageable);
	 */

	 @Query("select SUM(u.valor) from FaturaContrato u where u.status = ?1")
	Double findBySumValorAndStatus(String status);
}
