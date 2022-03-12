package com.nx.onetoone.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Dipendente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int dipendenteId;
	
	private String ufficio;
	
	@OneToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	public Dipendente() {
		super();
	}

	public Dipendente(String ufficio, Utente utente) {
		super();
		this.ufficio = ufficio;
		this.utente = utente;
	}

	public int getDipendenteId() {
		return dipendenteId;
	}

	public void setDipendenteId(int dipendenteId) {
		this.dipendenteId = dipendenteId;
	}

	public String getUfficio() {
		return ufficio;
	}

	public void setUfficio(String ufficio) {
		this.ufficio = ufficio;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
}
