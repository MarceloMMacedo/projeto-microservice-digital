package br.com.digital.dppessoas.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.com.digital.dppessoas.domain.Parceiro;
import br.com.digital.dppessoas.dtos.BaseDto;
import br.com.digital.dppessoas.repositories.ParceiroRepository;

@Service
public class ParceiroService extends ServiceBase<Parceiro> {

	@Autowired
	private ParceiroRepository repo;

	@Override
	public JpaRepository<Parceiro, Long> repo() {
		return repo;
	}

	@Override
	public Page<BaseDto> findPage(String name, Pageable pageable) {
		// TODO Auto-generated method stub
		return repo.findByNameIgnoreCaseContaining(name, pageable).map(x -> new BaseDto(x, downloadFile(x.getImagem())));
	}

	/*
	 * @Override public void uploadFile(MultipartFile file, Long id) { Parceiro
	 * c=find(id); c.setImagem(file.getName()); super.upload(file ); }
	 */
}
