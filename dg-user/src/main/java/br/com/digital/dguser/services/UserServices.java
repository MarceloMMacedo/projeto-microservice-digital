package br.com.digital.dguser.services;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.digital.dguser.domain.Role;
import br.com.digital.dguser.domain.User;
import br.com.digital.dguser.repositories.RoleRepository;
import br.com.digital.dguser.repositories.UserRepository;
import br.com.digital.dguser.services.email.EmailService;
import br.com.digital.dguser.services.exceptions.DataIntegrityException;

@Service
public class UserServices implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private UserRepository repo;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private EmailService emailService;

	@Autowired
	JWTUtils jwtUtils;

	public User insert(User obj) {

		// try {// obj.setEmpresa(user.getEmpresa());
		try {
			obj = repo.save(obj);
		} catch (Exception e) {
			throw new DataIntegrityException("Conta de Email já cadastrado, utilize outra conta");// TODO: handle exception
		}
		return obj;
		// } catch (DataIntegrityViolationException e ) { throw new
		// DataIntegrityException("Conta de Email já cadastrado, utilize outra conta"); }
	}

	public User update(User obj) {

		try {
			obj = repo.save(obj);
		} catch (Exception e) {
			throw new DataIntegrityException("Conta de Email já cadastrado, utilize outra conta");// TODO: handle exception
		}
		return obj;
	}

	public List<Role> roles() {
		return roleRepository.findAll();
	}

	public Role role(Long id) {
		Role role = roleRepository.findById(id).get();
		return role;
	}

	public void newpassword(Long id) {
		emailService.sendOrderConfirmationHtmlEmail(id);
	}

	public int resetpassword(Long id, String password, String token) {
		try {
			User user = repo.findById(id).get();
			user.setPassword(passwordEncoder.encode(password));
			repo.save(user);
		} catch (Exception e) {
			throw new DataIntegrityException("Conta de Email já cadastrado, utilize outra conta");// TODO: handle exception
		}
		return 0;

	}

}
