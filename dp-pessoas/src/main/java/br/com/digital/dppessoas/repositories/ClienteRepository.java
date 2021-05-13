package br.com.digital.dppessoas.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.dppessoas.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Page<Cliente> findByNameIgnoreCaseContaining(String name,Pageable pageable);
/*@Query("SELECT  new br.com.digital.dppessoas.dtos.BaseDto(o) FROM Cliente o ")
	List<BaseDto>  findPage(Pageable pageable);
	*/
}
