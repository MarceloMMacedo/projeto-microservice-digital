package br.com.digital.dppessoas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.dppessoas.domain.Cliente;
import br.com.digital.dppessoas.services.ClienteService;
import br.com.digital.dppessoas.services.ServiceBase;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController extends ControllerImp<Cliente> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClienteService service;

	@Override
	public ServiceBase<Cliente> service() {
		return service;
	}
	
	
	

}
