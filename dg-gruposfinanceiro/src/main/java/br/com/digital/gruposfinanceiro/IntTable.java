package br.com.digital.gruposfinanceiro;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.digital.gruposfinanceiro.domain.AgregadoTabValores;
import br.com.digital.gruposfinanceiro.domain.CentroCusto;
import br.com.digital.gruposfinanceiro.domain.TabelaValores;
import br.com.digital.gruposfinanceiro.repositories.AgregadoTabValoresRepository;
import br.com.digital.gruposfinanceiro.repositories.CentroCustoRepository;
import br.com.digital.gruposfinanceiro.repositories.TabelaValoresRepository;

@Service
public class IntTable {

	@Autowired
	CentroCustoRepository centroCustoRepository;

	@Autowired
	TabelaValoresRepository tabelaValoresRepository;

	@Autowired
	AgregadoTabValoresRepository agregadoTabValoresRepository;

	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	public void intTable() {

		CentroCusto centroCusto = new CentroCusto();
		centroCusto.setName("centro custo 1");
		centroCustoRepository.save(centroCusto);

		TabelaValores t = new TabelaValores();
		t.setName("Tabela A");
		tabelaValoresRepository.save(t);
		AgregadoTabValores agregadoTabValores=new AgregadoTabValores("Impostos", centroCusto, t,BigDecimal.valueOf( 0.05));
		
		agregadoTabValoresRepository.save(agregadoTabValores);
	}
}
