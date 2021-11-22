package com.generation.gestionale.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

/**
 * Usa Validation Api da usare insieme a @Valid nel controller
 */
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	/**
	 * validation
	 */
	@Size(max = 50, message = "troppo lungo")
	private String nome;
	
	@Size(max = 50, message = "troppo lungo")
	private String cognome;
	
	@Size(max = 20, message = "troppo lungo")
	private String telefono;
	
	@NotNull
	@NotEmpty
	@Email(message = "email non valida")
	private String email;
	
	@Size(max = 100, message = "troppo lungo")
	private String indirizzo;
	
	@Size(max = 50, message = "troppo lungo")
	private String citta;
	
	@Size(max = 30, message = "troppo lungo")
	private String regione;
	
//	@Size(max = 6, min = 0)
	@Min(value = 0)
	@Max(value = 6)
	private Short credito;
	
	/**
	 * necessario per la relazione con Ordini <br>
	 * mappedBy: nome della proprietà (foreign key) della classe corrispondente (Ordine.cliente)<br>
	 * JsonIgnore: non converte in json questa proprietà, che altrimenti va in loop
	 * infinito
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Ordine> ordini;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public Short getCredito() {
		return credito;
	}
	public void setCredito(Short credito) {
		this.credito = credito;
	}	
	
	public List<Ordine> getOrdini() {
		return ordini;
	}
	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}

	
	
	
}
