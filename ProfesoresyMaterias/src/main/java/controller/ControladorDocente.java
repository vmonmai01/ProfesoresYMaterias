package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Docente;

public class ControladorDocente {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("profesoresymaterias");

	/*
	 * 
	 */
	public static List<Docente> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNamedQuery("Docente.findAll");
		List<Docente> l = (List<Docente>) q.getResultList();
		em.close();
		return l;
	}

	/**
	 * Método para obtener varios registros a partir del nombre
	 * 
	 * @return
	 */
	public static List<Docente> findByLikeName(String name) {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM docente where lower(nombreCompleto) like ?", Docente.class);
		q.setParameter(1, "%" + name.toLowerCase() + "%");
		List<Docente> d = (List<Docente>) q.getResultList();
		em.close();
		return d;
	}

	/**
	 * Método para modificar un registro
	 */
	public static void update(Docente d) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(d);
		em.getTransaction().commit();
		em.close();
	}
}
