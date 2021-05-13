package br.com.digital.grmovimentofinanceiro.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digital.grmovimentofinanceiro.converter.StatusActiv;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class MovimentoService implements Serializable {

	private static final long serialVersionUID = 1L;
	@Autowired
	private FaturaServicosService faturaServicosService;

	public Double sumValorAbertoReceber() {
		return faturaServicosService.findBySumValorAndStatus(StatusActiv.ABERTO.getDescricao());
	}

	public Double sumValorAbertoPagar() {
		return 0.0;// faturaServicosService.findBySumValorAndStatus(StatusActiv.ABERTO.getDescricao());
	}

	public Double sumValorQuit() {
		return faturaServicosService.findBySumValorAndStatus(StatusActiv.QUIT.getDescricao());
	}
}
