  
package br.com.dao;

import java.util.List;
import javax.persistence.EntityManager;

import br.com.db.DB;
import br.com.db.DbException;
import br.com.model.Produto;

public class ProdutoDAO implements InterfaceDAO<Produto> {
	private EntityManager entityManager = null;

	public ProdutoDAO() {
		this.entityManager = DB.getEntityManager();
	}

	@Override
	public boolean inserir(Produto t) {
		System.out.println("--> Inserir produto");
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
	public Produto findById(int id) {
		System.out.println("--> Buscar produto por ID");

		try {
			Produto p = entityManager.find(Produto.class, id);
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
	public List<Produto> findAll() {
		System.out.println("--> Buscar todos os produtos");

		try {
			return entityManager.createQuery("FROM " + Produto.class.getName()).getResultList();
		} catch (Exception e) {
			System.out.println("Ocorreu um problema: " + e.getMessage());
			throw new DbException(e.getMessage());
		}
	}

	@SuppressWarnings("finally")
	@Override
	public boolean atualizar(Produto t) {
		System.out.println("--> Atualizar produto");
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
	public boolean deletar(Produto t) {
		System.out.println("--> Remover cliente");
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
