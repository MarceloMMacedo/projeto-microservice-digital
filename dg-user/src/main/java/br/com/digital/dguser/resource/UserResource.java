package br.com.digital.dguser.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.digital.dguser.converters.StatusActiv;
import br.com.digital.dguser.domain.Role;
import br.com.digital.dguser.domain.User;
import br.com.digital.dguser.repositories.UserRepository;
import br.com.digital.dguser.services.UserServices;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserServices service;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = repository.findById(id).get();
		obj.setRoles(service.role(obj.getIdrole()));
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/search")
	public ResponseEntity<User> findByEmail(@RequestParam String email) {
		User obj = repository.findByEmail(email);
		obj.setRoles(service.role(obj.getIdrole()));
		return ResponseEntity.ok(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Long> insert(@RequestBody User objDto, HttpServletRequest request) {

		objDto.setStatus(StatusActiv.ATIVO.getDescricao());
		objDto = service.insert(objDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objDto.getId()).toUri();

		return ResponseEntity.created(uri).body(objDto.getId());
	}
  
	@GetMapping(value = "/{id}/funcionario")
	public ResponseEntity<User> findByFuncionario(@PathVariable Long id) {
		User obj = repository.findByFuncionario(id);
		obj.setRoles(service.role(obj.getIdrole()));
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/{id}/cliente")
	public ResponseEntity<User> findByCliente(@PathVariable Long id) {
		User obj = repository.findByCliente(id);
		obj.setRoles(service.role(obj.getIdrole()));
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/{id}/resetpass")
	public ResponseEntity<String> resetpass(@PathVariable Long id) {
		User obj = repository.findByFuncionario(id);
		return ResponseEntity.ok(obj.getEmail());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody User objDto, @PathVariable Long id) {

		User obj = repository.save(objDto); // obj.setId(id);
											// obj =
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/roles")
	public ResponseEntity<List<Role>> rules() {
		return ResponseEntity.ok(service.roles());
	}

	@GetMapping(value = "/role/{id}")
	public ResponseEntity<Role> findRoleById(@PathVariable Long id) {
		Role obj = service.role(id);
		return ResponseEntity.ok(obj);
	}

	private Claims getClaims(String token) {
		Claims body = null;
		try {
			JwtParser parser = Jwts.parser();
			JwtParser setSigningKey = parser.setSigningKey(secret.getBytes());
			Jws<Claims> parseClaimsJws = setSigningKey.parseClaimsJws(token);
			body = parseClaimsJws.getBody();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		}
		try {

			return body;// Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		} catch (Exception e) {

			return null;
		}
	}

	@PostMapping(value = "/validartoken")
	public ResponseEntity<String> validartoken(@RequestBody String token) {
		String username;
		try {
			Claims claims = getClaims(token);
			if (claims != null) {
				username = claims.getSubject();
				Date expirationDate = claims.getExpiration();
				Date now = new Date(System.currentTimeMillis());

				if (username == null)
					return ResponseEntity.status(HttpStatus.LOCKED).body("Usuário Invalido");

				if (expirationDate == null)
					return ResponseEntity.status(HttpStatus.LOCKED).body("Data Expirada");
				if (now.after(expirationDate))
					return ResponseEntity.status(HttpStatus.LOCKED).body("Data Expirada");

			} else {
				return ResponseEntity.status(HttpStatus.LOCKED).body("Token Invalido");
			}

		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.status(HttpStatus.LOCKED).body("Token Invalido");
		}
		//
		return ResponseEntity.ok(username);
	}

	@PostMapping(value = "/newpassword")
	public ResponseEntity<String> newpassword(@RequestBody Long id) {
		try {
			service.newpassword(id);
		} catch (Exception e) {

			System.out.println(e);
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário inexistente");
		}
		//
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/resetpassword")
	public ResponseEntity<String> resetpassword(@RequestParam(value = "id") Long id,
			@RequestParam(value = "password") String password) {
		try {
			service.resetpassword(id, password, "");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário inexistente");
		}
		return ResponseEntity.noContent().build();
	}
}
