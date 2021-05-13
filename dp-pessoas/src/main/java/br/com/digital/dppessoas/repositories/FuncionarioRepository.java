package br.com.digital.dppessoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.dppessoas.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Page<Funcionario> findByNameIgnoreCaseContaining(String name,Pageable pageable);
/*@Query("SELECT  new br.com.digital.dppessoas.dtos.BaseDto(o) FROM Funcionario o ")
	List<BaseDto>  findPage(Pageable pageable);
	*/
}
