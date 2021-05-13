package br.com.digital.gruposfinanceiro.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.digital.gruposfinanceiro.domain.AgregadoTabValores;
import br.com.digital.gruposfinanceiro.domain.TabelaValores;
import br.com.digital.gruposfinanceiro.repositories.AgregadoTabValoresRepository;
import br.com.digital.gruposfinanceiro.repositories.TabelaValoresRepository;
import br.com.digital.gruposfinanceiro.services.exceptions.ObjectNotFoundException;
import br.com.digital.gruposfinanceiro.services.utils.UtilParameter;

@Service
public class TabelaValoresService extends ServiceBase<TabelaValores> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private TabelaValoresRepository repo;

	@Autowired
	private AgregadoTabValoresRepository agregadoTabValoresRepository;

	@Override
	public JpaRepository<TabelaValores, Long> repo() {
		return repo;
	}

	public AgregadoTabValores updateAgregadoTabValores(AgregadoTabValores obj) {

		try {
			obj = (AgregadoTabValores) UtilParameter._clonarentity(obj, new AgregadoTabValores());
			obj = agregadoTabValoresRepository.save(obj);
		} catch (Exception e) {
			throw new ObjectNotFoundException(e.getMessage());
		}

		return obj;
	}

	public AgregadoTabValores insertAgregadoTabValores(AgregadoTabValores obj, Long id) {
		try {
			TabelaValores t = find(id);
			obj.setTabelaValores(t);
		} catch (Exception e) {
			throw new ObjectNotFoundException("Erro em localizar Tabela");

		}
		obj = agregadoTabValoresRepository.save(obj);
		return obj;

	}

	public TabelaValores clone(Long id) {

		TabelaValores obj = (TabelaValores) UtilParameter._clonarentity_excludfield(find(id), new TabelaValores(),
				"id");
		obj = insert(obj);
		for (AgregadoTabValores a : find(id).getAgregadoTabValores()) {
			AgregadoTabValores valor = (AgregadoTabValores) UtilParameter._clonarentity_excludfield(a,
					new AgregadoTabValores(), "id");
			valor.setTabelaValores(obj);
			agregadoTabValoresRepository.save(valor);
		}

		return obj;

	}

}
