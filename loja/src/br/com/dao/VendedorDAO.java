package br.com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import br.com.db.DB;
import br.com.db.DbException;
import br.com.model.Vendedor;

public class VendedorDAO implements InterfaceDAO<Vendedor> {
	private EntityManager entityManager = null;
	
	public VendedorDAO() {
		this.entityManager = DB.getEntityManager();
	}
	
	@Override
	public boolean inserir(Vendedor t) {
		System.out.println("--> Inserir vendedor");
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
		
		return true;
	}

	@Override
	public Vendedor findById(int id) {
		System.out.println("--> Buscar vendedor por ID");
		
		try {
			Vendedor v = entityManager.find(Vendedor.class, id);
			if(v != null) {
				return v;
			}
		} catch(Exception e){
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Vendedor> findAll() {
		System.out.println("--> Buscar todos os vendedores");
		try {
			return entityManager.createQuery("FROM " + Vendedor.class.getName()).getResultList();
		}catch(Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
	}
	
	@SuppressWarnings("finally")
	@Override
	public boolean atualizar(Vendedor t) {
		System.out.println("--> Atualizar vendedor");
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		}catch(Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			return true;			
		}
	}

	@Override
	public boolean deletar(Vendedor t) {
		System.out.println("--> Remover vendedor");
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(t);
			this.entityManager.getTransaction().commit();
			return true;
		}catch(Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			return false;
		}
	}
	
}
