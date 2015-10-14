package org.Coddi.dao;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.Coddi.model.Usuario;

@Stateless
public class UsuarioDao {

	private EntityManager em;

	public UsuarioDao() {

	}

	public UsuarioDao(EntityManager em) {
		this.em = em;
	}

	public void create(Usuario entity) {
		entity.setDataRegistro(new Date());
		em.persist(entity);
	}

	public void deleteById(Long id) {
		Usuario entity = em.find(Usuario.class, id);
		if (entity != null) {
			em.remove(entity);
		}
	}

	public Usuario findById(Long id) {
		return em.find(Usuario.class, id);
	}

	public Usuario update(Usuario entity) {
		return em.merge(entity);
	}

	public List<Usuario> listAll(Integer startPosition, Integer maxResult) {
		TypedQuery<Usuario> findAllQuery = em.createQuery("SELECT DISTINCT u FROM Usuario u ORDER BY u.id", Usuario.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		return findAllQuery.getResultList();
	}

}
