package br.com.digital.dppessoas;

import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.digital.dppessoas.converters.StatusActiv;
import br.com.digital.dppessoas.converters.TipoPessoaEnum;
import br.com.digital.dppessoas.domain.Cliente;
import br.com.digital.dppessoas.repositories.ClienteRepository;

@Service
public class IntTable {

	@Autowired
	ClienteRepository clientesRepository;

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public void intTable() {

		Cliente c = new Cliente();

		c.setEndereco("R DELMIRO GOUVEIA", "63.050-216", "SALESIANOS", "JUAZEIRO DO NORTE", "1074", "", "CE",
				"ENDERECO PRINCIPAL", "63046460");

		c.setTipo(TipoPessoaEnum.Cliente.getDescricao());
		c.setName("Cliente");
		c.setIdempresa((long) 1);
		java.util.List<String> list = new LinkedList<String>();
		list.add("Administrador Geral");
		c.setNatureza("Pessoa Fisíca");
		// c.setRolers("Administrador Geral");

		c.setStatus(StatusActiv.ATIVO.getDescricao());
		c.setIduser((long) 1);
		c.setIdempresa((long) 1);
		c = clientesRepository.save(c);

	}
}
