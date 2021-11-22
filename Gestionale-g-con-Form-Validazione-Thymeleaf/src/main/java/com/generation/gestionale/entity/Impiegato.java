package com.generation.gestionale.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Usa Validation Api da usare insieme a @Valid nel controller
 */
@Entity
public class Impiegato {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * validation
	 */
	@NotBlank
	@Size(max = 50, message = "troppo lungo")
	private String nome;

	@NotBlank
	@Size(max = 50, message = "troppo lungo")
	private String cognome;

	@NotBlank
	@Size(max = 20, message = "troppo lungo")
	private String ruolo;

	@ManyToOne
	@JoinColumn(name = "rif_to", referencedColumnName = "id") // relazione ricorsiva con "id" di questa tabella
	private Impiegato rifTo;

	/**
	 * JsonIgnore nasconde lo stipendio quando restituisco il json tramite api rest
	 */
//	@JsonIgnore
	@DecimalMax(value = "9999.99")
	private BigDecimal stipendio;

	/**
	 * name: nome di questo campo sulla tabella <br>
	 * nullable: se la foreign key può essere nulla
	 */
	@ManyToOne
	@JoinColumn(name = "ufficio_id", nullable = false)
	private Ufficio ufficio;

	/**
	 * necessario per la relazione con Ordine <br>
	 * mappedBy: nome della proprietà (foreign key) della classe corrispondente (Ordine.impiegato)<br>
	 * JsonIgnore: non converte in json questa proprietà, che altrimenti va in loop
	 * infinito
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "impiegato")
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

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Impiegato getRifTo() {
		return rifTo;
	}

	public void setRifTo(Impiegato rifTo) {
		this.rifTo = rifTo;
	}

	public BigDecimal getStipendio() {
		return stipendio;
	}

	public void setStipendio(BigDecimal stipendio) {
		this.stipendio = stipendio;
	}

	public Ufficio getUfficio() {
		return ufficio;
	}

	public void setUfficio(Ufficio ufficio) {
		this.ufficio = ufficio;
	}

	public List<Ordine> getOrdini() {
		return ordini;
	}

	public void setOrdini(List<Ordine> ordini) {
		this.ordini = ordini;
	}



}
