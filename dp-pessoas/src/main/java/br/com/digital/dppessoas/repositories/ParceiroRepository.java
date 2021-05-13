package br.com.digital.dppessoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.dppessoas.domain.Parceiro;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {

	Page<Parceiro> findByNameIgnoreCaseContaining(String name,Pageable pageable);
/*@Query("SELECT  new br.com.digital.dppessoas.dtos.BaseDto(o) FROM Funcionario o ")
	List<BaseDto>  findPage(Pageable pageable);
	*/
}
