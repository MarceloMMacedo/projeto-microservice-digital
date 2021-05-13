package br.com.digital.grmovimentofinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.grmovimentofinanceiro.domain.FaturaContrato;
import br.com.digital.grmovimentofinanceiro.services.FornecedorService;
import br.com.digital.grmovimentofinanceiro.services.ServiceBase;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedoresController extends ControllerImp<FaturaContrato> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FornecedorService service;

	@Override
	public ServiceBase<FaturaContrato> service() {
		return service;
	}
	
	
	

}
