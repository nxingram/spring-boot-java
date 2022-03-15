package com.nx.onetoone.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int utenteId;
	
	private String email;
	private String password;
	
	//opzionale - relazione inversa
//	@OneToOne(mappedBy = "utente")
//	private Dipendente dipendente;

	//opzionale - relazione inversa
//	@OneToOne(mappedBy = "utente")
//	private Cliente cliente;
	
	public Utente() {
		super();
	}

	public Utente(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public int getUtenteId() {
		return utenteId;
	}

	public void setUtenteId(int utenteId) {
		this.utenteId = utenteId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public Dipendente getDipendente() {
//		return dipendente;
//	}
//
//	public void setDipendente(Dipendente dipendente) {
//		this.dipendente = dipendente;
//	}
//
//	public Cliente getCliente() {
//		return cliente;
//	}
//
//	public void setCliente(Cliente cliente) {
//		this.cliente = cliente;
//	}
	
	
	
	
	
}
