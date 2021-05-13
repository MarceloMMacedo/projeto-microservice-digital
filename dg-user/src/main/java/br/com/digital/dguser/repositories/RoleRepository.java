package br.com.digital.dguser.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.digital.dguser.domain.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
