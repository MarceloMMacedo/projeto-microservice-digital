package br.com.digital.gruposfinanceiro.services;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digital.gruposfinanceiro.domain.BaseEntity;
import br.com.digital.gruposfinanceiro.dtos.BaseDto;
import br.com.digital.gruposfinanceiro.faingbases.UserFeingClient;
import br.com.digital.gruposfinanceiro.services.exceptions.DataIntegrityException;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Data
@Log4j2
@NoArgsConstructor
public class ServiceBase<T extends BaseEntity> {
	
	public JpaRepository<T, Long> repo() {
		return null;
	}

	T obj;

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

	/**
	 * @param id
	 * @return objeto por id
	 */

	public T find(Long id) {
		try {
			obj = (T) repo().findById(id).get();
			log.info("localizar objeto:" + getClasse().getSimpleName() + " por id");
		} catch (Exception e) {
			log.error("erro localizar objeto:" + getClasse().getSimpleName() + " por id, descrição:" + e.getMessage());
			return null;
		}
		return obj;
	}

	/**
	 * @param obj
	 * @return insere objeto no banco de dados
	 */

	public T insert(T obj) {

		// obj.setEmpresa(user.getEmpresa());
		try {

			repo().save(obj);
			log.info("inserindo  objeto:" + getClasse().getSimpleName() + " - " + obj.toString());
		} catch (Exception e) {
			log.error("erro de inserção objeto:" + getClasse().getSimpleName() + ", descrição:" + e.getMessage());
		}

		return obj;
	}

	/**
	 * @param obj
	 * @return objeto atualizado
	 */

	public T update(T obj, Long id) {

		try {
			repo().save(obj);
			log.info("atualização  objeto:" + getClasse().getSimpleName() + " com sucesso, id:" + "" + obj.getId());
		} catch (Exception e) {
			log.error("erro de inserção objeto:" + getClasse().getSimpleName() + ", descrição:" + e.getMessage());
		}
		return obj;
	}

	/**
	 * delete from id
	 * 
	 * @param id
	 */

	public void delete(Long id) {

		// find(id);
		try {
			repo().delete(repo().findById(id).get());
			log.info("deleção do objeto:" + getClasse().getSimpleName() + " com sucesso, id:" + "" + id);

		} catch (DataIntegrityViolationException e) {
			log.error("Não é possível excluir porque há pedidos relacionados");
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
		}

	}

	/**
	 * @return lista de itens
	 */

	public List<T> findAll() {
		List<T> find = (List<T>) repo().findAll();
		log.info("lista de itens da classe:" + getClasse().getSimpleName() + " gerado com sucesso");
		return find;
	}

	/**
	 * @param email
	 * @return objeto por email
	 */

	public T findByEmail(String email) {
		return null;
	}

	/**
	 * @param name
	 * @return lista de objetos pelo name
	 */
	public Page<BaseDto> findPage(String name, Pageable pageable) {
		return null;
	}

	public List<BaseDto> findBaseAll() {
		return null;
	}

	public List<T> findAllName(String name) {

		return null;
	}

	public byte[] ViewPdf() {
		// TODO Auto-generated method stub
		return null;
	}

	// gdrive
}
