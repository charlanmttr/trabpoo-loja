package br.com.dao;

import java.util.List;
import javax.persistence.EntityManager;

import br.com.db.DB;
import br.com.db.DbException;
import br.com.model.Cliente;

public class ClienteDAO implements InterfaceDAO<Cliente> {
	private EntityManager entityManager = null;

	public ClienteDAO() {
		this.entityManager = DB.getEntityManager();
	}

	@Override
	public boolean inserir(Cliente t) {
		System.out.println("--> Inserir cliente");
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
		return true;
	}

	@Override
	public Cliente findById(int id) {
		System.out.println("--> Buscar cliente por ID " + id);
		try {
			Cliente p = entityManager.find(Cliente.class, id);
			if (p != null) {
				return p;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
		return null;
	}

	@Override
	public List<Cliente> findAll() {
		System.out.println("--> Buscar todos os clientes");

		try {
			return entityManager.createQuery("FROM " + Cliente.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	@Override
	public boolean atualizar(Cliente t) {
		System.out.println("--> Atualizar cliente "+ t.getId());
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(t);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		} finally {
			return true;
		}
	}

	@Override
	public boolean deletar(Cliente t) {
		System.out.println("--> Remover cliente " + t.getId());
		try {
			this.entityManager.getTransaction().begin();
			this.entityManager.remove(t);
			this.entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			return false;
		}
	}

}