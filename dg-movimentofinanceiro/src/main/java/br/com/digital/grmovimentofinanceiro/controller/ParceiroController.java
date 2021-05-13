package br.com.digital.grmovimentofinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.grmovimentofinanceiro.domain.Parceiro;
import br.com.digital.grmovimentofinanceiro.services.ParceiroService;
import br.com.digital.grmovimentofinanceiro.services.ServiceBase;

@RestController
@RequestMapping(value = "/parceiros")
public class ParceiroController extends ControllerImp<Parceiro> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ParceiroService service;

	@Override
	public ServiceBase<Parceiro> service() {
		return service;
	}

}
