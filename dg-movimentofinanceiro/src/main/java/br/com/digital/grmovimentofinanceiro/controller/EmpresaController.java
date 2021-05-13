package br.com.digital.grmovimentofinanceiro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.digital.grmovimentofinanceiro.domain.FaturaServicos;
import br.com.digital.grmovimentofinanceiro.domain.Empresa;
import br.com.digital.grmovimentofinanceiro.services.FaturaServicosService;
import br.com.digital.grmovimentofinanceiro.services.ServiceBase;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

	private static final long serialVersionUID = 1L;

	@Autowired
	private Empresa empresa;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Empresa> findAll() {
		return ResponseEntity.ok().body(empresa.getEmpresa());
	}

}
