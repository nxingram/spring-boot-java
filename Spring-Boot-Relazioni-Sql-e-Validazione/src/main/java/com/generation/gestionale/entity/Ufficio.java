package com.generation.gestionale.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Usa Validation Api da usare insieme a @Valid nel controller
 */
@Entity
public class Ufficio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * validation
	 */
	@Size(max = 30, message = "troppo lungo")
	private String nome;

	// manca tabella corrispondente
	private Integer luogo_id;

	/**
	 * necessario per la relazione con Impiegato <br>
	 * mappedBy: nome della proprietà (foreign key) della classe corrispondente (Impiegato.ufficio)<br>
	 * JsonIgnore: non converte in json questa proprietà, che altrimenti va in loop
	 * infinito
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "ufficio")
	private List<Impiegato> impiegato;

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

	public Integer getLuogo_id() {
		return luogo_id;
	}

	public void setLuogo_id(Integer luogo_id) {
		this.luogo_id = luogo_id;
	}

	public List<Impiegato> getImpiegato() {
		return impiegato;
	}

	public void setImpiegato(List<Impiegato> impiegato) {
		this.impiegato = impiegato;
	}
	
}
