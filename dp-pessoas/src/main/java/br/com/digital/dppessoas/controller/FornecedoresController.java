package br.com.digital.dppessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.dppessoas.domain.Fornecedor;
import br.com.digital.dppessoas.services.FornecedorService;
import br.com.digital.dppessoas.services.ServiceBase;

@RestController
@RequestMapping(value = "/fornecedores")
public class FornecedoresController extends ControllerImp<Fornecedor> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FornecedorService service;

	@Override
	public ServiceBase<Fornecedor> service() {
		return service;
	}
	
	
	

}
