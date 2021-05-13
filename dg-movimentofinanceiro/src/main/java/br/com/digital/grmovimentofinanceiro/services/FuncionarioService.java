package br.com.digital.grmovimentofinanceiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.digital.grmovimentofinanceiro.domain.FaturaSaida;
import br.com.digital.grmovimentofinanceiro.dtos.BaseDto;
import br.com.digital.grmovimentofinanceiro.repositories.FaturaSaidaRepository;

@Service
public class FuncionarioService extends ServiceBase<FaturaSaida> {

	@Autowired
	private FaturaSaidaRepository repo;

	@Override
	public JpaRepository<FaturaSaida, Long> repo() {
		return repo;
	}

	@Override
	public Page<BaseDto> findPage(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByNameIgnoreCaseContaining(name, pageable).map(x -> new BaseDto(x, downloadFile(x.getImagem())));
	}

	/*
	 * @Override public void uploadFile(MultipartFile file, Long id) { FaturaSaida
	 * c=find(id); c.setImagem(file.getName()); super.upload(file ); }
	 */
}
