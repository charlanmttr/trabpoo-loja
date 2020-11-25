package br.com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Vendedor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nomeCompleto;
	private String login;
	private String setorVenda;
	private String aniversario;
	
	public Vendedor(int id, String nomeCompleto, String login, String setorVenda, String aniversario) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.aniversario = aniversario;
		this.setorVenda = setorVenda;
		this.login = login;
	}

	public Vendedor() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSetorVenda() {
		return setorVenda;
	}

	public void setSetorVenda(String setorVenda) {
		this.setorVenda = setorVenda;
	}

	public String getAniversario() {
		return aniversario;
	}

	public void setAniversario(String aniversario) {
		this.aniversario = aniversario;
	}
}
