package br.com.digital.grmovimentofinanceiro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.digital.grmovimentofinanceiro.domain.FaturaServicos;

@Repository
public interface FaturaServicosRepository extends JpaRepository<FaturaServicos, Long> {

	Page<FaturaServicos> findByNameIgnoreCaseContaining(String name,Pageable pageable);
/*@Query("SELECT  new br.com.digital.grmovimentofinanceiro.dtos.BaseDto(o) FROM FaturaServicos o ")
	List<BaseDto>  findPage(Pageable pageable);
	*/
	 @Query("select SUM(u.valor) from FaturaServicos u where u.status = ?1 group by u.id")
		Double findBySumValorAndStatus(String status);
}
