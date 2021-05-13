package br.com.digital.grmovimentofinanceiro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.grmovimentofinanceiro.converter.StatusActiv;
import br.com.digital.grmovimentofinanceiro.domain.FaturaServicos;
import br.com.digital.grmovimentofinanceiro.services.FaturaServicosService;
import br.com.digital.grmovimentofinanceiro.services.MovimentoService;
import br.com.digital.grmovimentofinanceiro.services.ServiceBase;

@RestController
@RequestMapping(value = "/movimentos")
public class MovimentoController  {

	private static final long serialVersionUID = 1L;

	@Autowired
	private MovimentoService service;

	 
	
	@GetMapping(value = "/sumvalorabertoreceber")
	public ResponseEntity<Double> sumValorAbertoReceber() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.sumValorAbertoReceber( ));
	}
	@GetMapping(value = "/sumvalorabertopagar")
	public ResponseEntity<Double> sumValorAbertoPagar() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.sumValorAbertoPagar( ));
	}
	@GetMapping(value = "/sumvalorquit")
	public ResponseEntity<Double> sumValorQuit() {
		// TODO Auto-generated method stub
		return ResponseEntity.ok(service.sumValorQuit());
	}

}
