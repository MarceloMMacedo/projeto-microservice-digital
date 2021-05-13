package br.com.digital.grmovimentofinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.grmovimentofinanceiro.domain.FaturaSaida;
import br.com.digital.grmovimentofinanceiro.services.FuncionarioService;
import br.com.digital.grmovimentofinanceiro.services.ServiceBase;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController extends ControllerImp<FaturaSaida> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FuncionarioService service;

	@Override
	public ServiceBase<FaturaSaida> service() {
		return service;
	}

}
