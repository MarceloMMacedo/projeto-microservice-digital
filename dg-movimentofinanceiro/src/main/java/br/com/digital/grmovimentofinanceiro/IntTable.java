package br.com.digital.grmovimentofinanceiro;

import java.math.BigDecimal;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.digital.grmovimentofinanceiro.converter.StatusActiv;
import br.com.digital.grmovimentofinanceiro.converter.TipoPessoaEnum;
import br.com.digital.grmovimentofinanceiro.domain.FaturaContrato;
import br.com.digital.grmovimentofinanceiro.domain.FaturaServicos;
import br.com.digital.grmovimentofinanceiro.repositories.FaturaContratoRepository;
import br.com.digital.grmovimentofinanceiro.repositories.FaturaServicosRepository;

@Service
public class IntTable {

	@Autowired
	FaturaContratoRepository clientesRepository;

	@Autowired
	FaturaServicosRepository faturaServicosRepository;

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public void intTable() {

		FaturaContrato c = new FaturaContrato();

		BigDecimal value = BigDecimal.valueOf(12.36);

		c.setValor(value);
		c.setName("Cliente");

		c.setStatus(StatusActiv.ABERTO.getDescricao());
		c = clientesRepository.save(c);

		FaturaServicos s = new FaturaServicos();

		value = BigDecimal.valueOf(12.36);

		s.setValor(value);
		s.setName("Cliente");

		s.setStatus(StatusActiv.ABERTO.getDescricao());
		s = faturaServicosRepository.save(s);

	}
}
