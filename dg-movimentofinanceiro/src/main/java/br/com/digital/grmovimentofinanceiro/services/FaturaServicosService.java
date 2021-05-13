package br.com.digital.grmovimentofinanceiro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.digital.grmovimentofinanceiro.domain.FaturaServicos;
import br.com.digital.grmovimentofinanceiro.dtos.BaseDto;
import br.com.digital.grmovimentofinanceiro.repositories.FaturaServicosRepository;

@Service
public class FaturaServicosService extends ServiceBase<FaturaServicos> {

	@Autowired
	private FaturaServicosRepository repo;

	@Override
	public JpaRepository<FaturaServicos, Long> repo() {
		return repo;
	}

	@Override
	public Page<BaseDto> findPage(String name, Pageable pageable) {
		return repo.findByNameIgnoreCaseContaining(name, pageable).map(x -> new BaseDto(x, downloadFile(x.getImagem())));
	}

	@Override
	public Double findBySumValorAndStatus(String status) { 
		Double v=(double) 0;
		try {
			v += repo.findBySumValorAndStatus(status);
		} catch (Exception e) {
			 
		}
		
		return v;
	}
	/*
	 * @Override public void uploadFile(MultipartFile file, Long id) { FaturaServicos
	 * c=find(id); c.setImagem(file.getName()); super.upload(file ); }
	 */
}
