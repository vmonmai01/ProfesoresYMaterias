package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asignatura;
import model.Asignaturaspordocente;
import model.Docente;

public class ControladorAsignatura {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("profesoresymaterias");

	/*
	 * 
	 */
	public static List<Asignatura> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNamedQuery("Asignatura.findAll");
		List<Asignatura> a = (List<Asignatura>) q.getResultList();
		em.close();
		return a;
	}

	/**
	 * Método para modificar un registro
	 */
	public static void update(Asignatura a) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(a);
		em.getTransaction().commit();
		em.close();
	}

}
