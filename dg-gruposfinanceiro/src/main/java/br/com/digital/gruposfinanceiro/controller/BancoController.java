package br.com.digital.gruposfinanceiro.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.gruposfinanceiro.domain.Banco;
import br.com.digital.gruposfinanceiro.services.BancoService;
import br.com.digital.gruposfinanceiro.services.ServiceBase;

@RestController
@RequestMapping("/bancos")
public class BancoController extends ControllerImp<Banco> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BancoService service;

	@Override
	public ServiceBase<Banco> service() {
		return service;
	}

}
