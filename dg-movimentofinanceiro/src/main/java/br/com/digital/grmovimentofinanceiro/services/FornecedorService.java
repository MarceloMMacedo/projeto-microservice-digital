package br.com.digital.grmovimentofinanceiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.digital.grmovimentofinanceiro.domain.FaturaContrato;
import br.com.digital.grmovimentofinanceiro.dtos.BaseDto;
import br.com.digital.grmovimentofinanceiro.repositories.FaturaContratoRepository; 

@Service
public class FornecedorService extends ServiceBase<FaturaContrato> {

	@Autowired
	private FaturaContratoRepository repo;

	@Override
	public JpaRepository<FaturaContrato, Long> repo() {
		return repo;
	}

	@Override
	public Page<BaseDto> findPage(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByNameIgnoreCaseContaining(name, pageable).map(x -> new BaseDto(x, downloadFile(x.getImagem())));
	}

	/*
	 * @Override public void uploadFile(MultipartFile file, Long id) { FaturaContrato
	 * c=find(id); c.setImagem(file.getName()); super.upload(file ); }
	 */
}
