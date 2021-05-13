package br.com.digital.dguser.services.email;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Date;
import java.util.HashMap;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.digital.dguser.domain.User;
import br.com.digital.dguser.repositories.UserRepository;
import br.com.digital.dguser.services.JWTUtils;
import br.com.digital.dguser.services.exceptions.AuthorizationException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class EmailService {

	@Value("${default.sender}")
	private String sender;

	private HashMap<String, String> mapa = new HashMap<>();

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	UserRepository repo;

	@Autowired
	ResourceLoader resourceLoader;
	@Autowired
	private MailSender mailSender;

	public void sendOrderConfirmationEmail(Long id) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(id);
		sendEmail(sm);
	}

	public void sendEmail(SimpleMailMessage msg) {
		mailSender.send(msg);
	}

	public void sendHtmlEmail(MimeMessage msg) {
		log.info("Enviando email htmal...");
		javaMailSender.send(msg);
		log.info("Email enviado");
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Long id) {
		SimpleMailMessage sm = new SimpleMailMessage();
		User obj = repo.findById(id).get();
		sm.setTo(obj.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido confirmado! Código: " + obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}

	public void sendNewPasswordEmail(Long cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente, newPass);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareNewPasswordEmail(Long id, String newPass) {
		SimpleMailMessage sm = new SimpleMailMessage();
		User obj = repo.findById(id).get();
		sm.setTo(obj.getEmail());
		sm.setTo(sender);
		sm.setSubject("Solicitação de nova senha");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Nova senha: " + newPass);
		return sm;
	}

	public void sendOrderConfirmationHtmlEmail(Long obj) {
		MimeMessage mm = null;
		mm = prepareMemilMessageFromPedido(obj);
		sendHtmlEmail(mm);
	}

	protected MimeMessage prepareMemilMessageFromPedido(Long id) {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = null;
		User user = repo.findById(id).get();

		if (user == null) {
			// throw new IllegalArgumentException("Usuário inexistente");
		}
		// sm.setTo(obj.getUser().getEmail());
		try {
			mmh = new MimeMessageHelper(mimeMessage, true);

			mmh.setTo(user.getEmail());// destinatario
			mmh.setFrom(sender);// remetente
			mmh.setSubject("Pedido de senha ");
			mmh.setSentDate(new Date(System.currentTimeMillis()));
			String token = jwtUtils.generateToken(user.getEmail());
			if (user.getTipouser().equals("Funcionario")) {
				mmh.setText(htmlFromTemplateResetFuncionario(user.getEmail()), true);
			}else {
				 {
						mmh.setText(htmlFromTemplateResetCliente(user.getEmail()), true);
					}
			}
			/*
			 * mmh.
			 * setText("<h1 style=\"color: #5e9ca0;\">Atualiza&ccedil;&atilde;o de Senha!</h1>\n"
			 * +
			 * "<h2 style=\"color: #2e6c80;\">Link para Atualiza&ccedil;&otilde;a de sua senha:"
			 * +
			 * "<a title=\"Atualizar Senha\" href=\"link here\" target=\"_blank\" rel=\"noopener\">Nova Senha</a>"
			 * + "</h2>\n" +"<p><a href='teste'> teste Login </a> </p>" +
			 * "<p>V&aacute;lido somente por 4h apos sua solicit&ccedil;&atilde;o.</p> "
			 * +"<p> " + token +" </p>" , true);
			 * 
			 */
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mmh.getMimeMessage();
	}

	public String loadPathJasperFile(String file) {
		Resource res = resourceLoader.getResource("classpath:templates/email/");

		try {
			Path reportFile = new File(res.getURL().getPath()).toPath();// Paths.get(res.getURL() );

			reportFile = reportFile.resolve(file);
			return reportFile.toString();
		} catch (IOException e) {
			// log.error("Error while trying to get file prefix", e);
			throw new AuthorizationException("Could not load file", e);
		}
		// @formatter:on
	}

	protected String htmlFromTemplateResetFuncionario(String obj) {
		Context context = new Context();
		String token = jwtUtils.generateToken(obj);
		context.setVariable("token", token);
		return templateEngine.process(loadPathJasperFile("/email/newpasswordfuncionario.html"), context);
	}
	protected String htmlFromTemplateResetCliente(String obj) {
		Context context = new Context();
		String token = jwtUtils.generateToken(obj);
		context.setVariable("token", token);
		return templateEngine.process(loadPathJasperFile("/email/newpasswordcliente.html"), context);
	}
}
