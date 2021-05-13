package br.com.digital.dppessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.dppessoas.domain.Funcionario;
import br.com.digital.dppessoas.services.FuncionarioService;
import br.com.digital.dppessoas.services.ServiceBase;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController extends ControllerImp<Funcionario> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FuncionarioService service;

	@Override
	public ServiceBase<Funcionario> service() {
		return service;
	}

}
