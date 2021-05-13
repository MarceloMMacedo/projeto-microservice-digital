package br.com.digital.gruposfinanceiro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.gruposfinanceiro.domain.CentroCusto;

@Repository
public interface CentroCustoRepository extends JpaRepository<CentroCusto, Long> {

	Page<CentroCusto> findByNameIgnoreCaseContaining(String name,Pageable pageable);
/*@Query("SELECT  new br.com.digital.gruposfinanceiro.dtos.BaseDto(o) FROM Banco o ")
	List<BaseDto>  findPage(Pageable pageable);
	*/
}
