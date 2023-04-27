package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asignatura;
import model.Asignaturaspordocente;
import model.Docente;

public class ControladorAsignaturaPorDocente {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("profesoresymaterias");

	/*
	 * 
	 */
	public static List<Asignaturaspordocente> findAll() {
		EntityManager em = entityManagerFactory.createEntityManager();
		Query q = em.createNamedQuery("Asignaturaspordocente.findAll");
		List<Asignaturaspordocente> l = (List<Asignaturaspordocente>) q.getResultList();
		em.close();
		return l;
	}

	/**
	 * Método para modificar un registro
	 */
	public static void update(Asignaturaspordocente v) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(v);
		System.out.println("La actualizacion de datos se realizo correctamente");
		em.getTransaction().commit();
		em.close();
	}

	/*
	 * 
	 */
	public static void insert(Asignaturaspordocente v) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(v);
		System.out.println("La entrada de nuevos datos se realizo correctamente");
		em.getTransaction().commit();
		em.close();
	}

	public static Asignaturaspordocente asignaturaDocenteProfesor(Docente d, Asignatura a) {
		Asignaturaspordocente v = null;
		EntityManager em = entityManagerFactory.createEntityManager();
		try {
			Query q = em.createNativeQuery("select * from asignaturaspordocente where idDocente = " + d.getId()
					+ " and idAsignatura = " + a.getId() + ";", Asignaturaspordocente.class);
			v = (Asignaturaspordocente) q.getSingleResult();

		} catch (Exception e2) {
		}
		em.close();

		return v;
	}

}
