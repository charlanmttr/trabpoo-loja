package br.com.dao;

import java.util.List;

public interface InterfaceDAO <T> {
	
	public boolean inserir(T t);
	
	public T findById(int id);
	public List<T> findAll();
	
	public boolean atualizar(T t); 
	
	public boolean deletar(T t);
}
