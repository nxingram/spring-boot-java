package com.nx.springtutorial.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/*
 * ATTENZIONE:
 * ricordarsi di aggiunge la dipendency Data Jpa e MySql Driver
 * altrimenti non trovo l'annotazione @Entity, @Id, etc.
 * e non si connette al database
 */

@Entity // questa classe corrisponde all'entità sul database
@Table(name = "studente_es")
public class Studente {

	@Id // questa è la chiave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // ci pensa mysql a generare l'id e ad auto-incrementarlo
	private int id;
	
	private String nome;
	private String cognome;
	private String email;
	
	public Studente() { // costruttore vuoto obbligatorio
	}

	public Studente(String nome, String cognome, String email) {
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
	}

	// getters e setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Studente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + "]";
	}
	
}
