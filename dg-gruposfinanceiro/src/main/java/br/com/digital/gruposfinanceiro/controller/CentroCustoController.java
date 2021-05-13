package br.com.digital.gruposfinanceiro.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.gruposfinanceiro.domain.CentroCusto;
import br.com.digital.gruposfinanceiro.services.CentroCustoService;
import br.com.digital.gruposfinanceiro.services.ServiceBase;

@RestController
@RequestMapping("/centrocustos")
public class CentroCustoController extends ControllerImp<CentroCusto> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CentroCustoService service;

	@Override
	public ServiceBase<CentroCusto> service() {
		return service;
	}

}
