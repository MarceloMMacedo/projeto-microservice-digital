package br.com.digital.dppessoas.faingbases;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.digital.dppessoas.dtos.Role;
import br.com.digital.dppessoas.dtos.User;

@Component
@FeignClient(name = "dg-user", path = "/users")
public interface UserFeingClient {
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Long> insert(  @RequestBody User objDto);

	@GetMapping(value = "/{id}/cliente")
	public ResponseEntity<User> findByCliente(@PathVariable Long id) ;
	
	@GetMapping(value = "/search")
	ResponseEntity<User> findByEmail(@RequestParam String email);	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update( @RequestBody User objDto, @PathVariable Long id);
	
	@GetMapping(value = "/role/{id}")
	public ResponseEntity<Role> findRoleById(@PathVariable Long id) ;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id);
	
	@GetMapping(value = "/roles")
	public ResponseEntity<List<Role>> rules();
}
