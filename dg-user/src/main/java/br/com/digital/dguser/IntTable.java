package br.com.digital.dguser;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.digital.dguser.converters.SimNaoEnum;
import br.com.digital.dguser.converters.StatusActiv;
import br.com.digital.dguser.converters.TipoUserEnum;
import br.com.digital.dguser.domain.Role;
import br.com.digital.dguser.domain.User;
import br.com.digital.dguser.repositories.RoleRepository;
import br.com.digital.dguser.repositories.UserRepository;

@Service
public class IntTable {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public void intTable() {

		Role r = new Role((long) 1, "ROLE_ADMIN", "Administrador");
		roleRepository.save(r);

		r = new Role((long) 2, "ROLE_OPERATOR", "Operador");
		roleRepository.save(r);
		
		r = new Role((long) 3, "ROLE_FINAN", "Financeiro");
		roleRepository.save(r);
		
		r = new Role((long) 4, "ROLE_CLIENTE", "Cliente");
		roleRepository.save(r);

		User u = new User("admin", "marcelo_macedo01@hotmail.com",
				"$2a$10$H/YujdXbUXHRtJ.3FdE8YeBhoKjyTy4uM2M/1rPyd.sx/T7bJ/EOy");
		u.setStatus(StatusActiv.ATIVO.getDescricao()); 
		Set<Role> r1 = new HashSet<>();
		r1.add(new Role((long) 1, "ROLE_ADMIN"));
		u.setRoles(new Role((long) 1, "ROLE_ADMIN", "Administrador"));
		u.setIdrole((long) 1);
		u.setTipouser(TipoUserEnum.FUNCIONARIO.getDescricao());
		u.setFuncionario((long) 1);
		userRepository.save(u);

		u = new User("cliente", "marcelo_macedo01@hotmail1.com",
				"$2a$10$H/YujdXbUXHRtJ.3FdE8YeBhoKjyTy4uM2M/1rPyd.sx/T7bJ/EOy");
		u.setStatus(StatusActiv.ATIVO.getDescricao()); 

		u.setIdrole((long) 4);
		u.setTipouser(TipoUserEnum.Cliente.getDescricao());
		u.setCliente((long) 1);
		userRepository.save(u);

	}
}
