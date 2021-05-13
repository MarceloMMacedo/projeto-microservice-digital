package br.com.dgoauth.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.dgoauth.entities.User;

@RestController
@RequestMapping(value = "/acesso")
public class UserOauth {
	@GetMapping( value = "/user")
	public ResponseEntity<User> getuser(){
		try {
			User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			return ResponseEntity.ok(user);
		}
		catch (IllegalArgumentException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

}
