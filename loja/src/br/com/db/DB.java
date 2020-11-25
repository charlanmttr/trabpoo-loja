package br.com.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class DB {
	
	private static String entityManager = "loja";
	
	private static EntityManagerFactory emf = null;
	private static EntityManager em = null;
	
	public static EntityManager getEntityManager() {

		if (em == null) {
			try {
				new Persistence();
				emf = Persistence.createEntityManagerFactory(entityManager);
				em = emf.createEntityManager();
			} catch (Exception e) {
				e.printStackTrace();
				throw new DbException(e.getMessage());
			}
		}
		return em;
	}
}
