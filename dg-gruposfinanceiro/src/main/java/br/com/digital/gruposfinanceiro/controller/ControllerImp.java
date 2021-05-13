package br.com.digital.gruposfinanceiro.controller;
 

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digital.gruposfinanceiro.domain.BaseEntity;
import br.com.digital.gruposfinanceiro.dtos.BaseDto;
import br.com.digital.gruposfinanceiro.dtos.PessoaDTO;
import br.com.digital.gruposfinanceiro.dtos.Role;
import br.com.digital.gruposfinanceiro.faingbases.UserFeingClient;
import br.com.digital.gruposfinanceiro.services.ServiceBase;
import br.com.digital.gruposfinanceiro.services.utils.FilesService;
import br.com.digital.gruposfinanceiro.services.utils.UtilParameter;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
 
@Log4j2
public class ControllerImp<T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	FilesService filesService;
	
	@Autowired
	private UserFeingClient userFeingClient ;

	public ServiceBase<T> service() {
		return null;
	}
 
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<T>> findAll() {
		List<T> list = service().findAll();
		return ResponseEntity.ok().body(list);
	}

	public Class<T> getClasse() {
		Class<T> classe = null;
		try {
			Class<T> class1 = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];

			classe = class1;
		} catch (Exception e) {
		}
		return classe;
	}

 
	// @PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST' )")
	@PostMapping
	public ResponseEntity<Long> insert(@RequestBody T objDto) {
		objDto.setId(null);
		objDto = service().insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDto.getId()).toUri();
		return ResponseEntity.created(uri).body(objDto.getId());
	}

	// @PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST' )")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody T objDto, @PathVariable Long id) {

		@SuppressWarnings("unused") 
		T obj = service().update(objDto,id); // obj.setId(id);
											// obj =
		return ResponseEntity.noContent().build();
	}

	// @PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST' )")

	@GetMapping(value = "/search")
	public ResponseEntity<T> findemail(String email) {
		// TODO Auto-generated method stub
		return ResponseEntity.ok().body(service().findByEmail(email));
	}

	// @PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST' )")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<BaseDto>> findPage(@RequestParam(value = "name", defaultValue="") String name, Pageable pageable) {
		return ResponseEntity.ok().body(service().findPage(name,pageable));
	}

	// @PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST' )")
	public ResponseEntity<String> uploadProfilePicture(@RequestParam(name = "file") MultipartFile file,
			@PathVariable Long id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	// @PreAuthorize("hasAnyRole('ROLE_ADMG' , 'ROLE_OPF' , 'ROLE_ADMEST' )")
	@RequestMapping(value = "/baseall", method = RequestMethod.GET)
	public ResponseEntity<List<BaseDto>> findBaseAll() {

		return ResponseEntity.ok().body(service().findBaseAll());
	}
 
 
}