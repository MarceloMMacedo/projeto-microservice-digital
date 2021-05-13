package br.com.digital.dguser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.digital.dguser.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Transactional(readOnly = true)
	User findByEmailAndTipouser(String email, String tipouser);

	@Transactional(readOnly = true)
	User findByEmail(String email);

	@Transactional(readOnly = true)
	User findByFuncionario(Long funcionario);

	@Transactional(readOnly = true)
	User findByCliente(Long cliente);
}
