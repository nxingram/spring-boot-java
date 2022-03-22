package com.generation.gestionale.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Usa Validation Api da usare insieme a @Valid nel controller
 */
@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	/**
	 * name: nome di questo campo sulla tabella <br>
	 * nullable: se la foreign key può essere nulla
	 */
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;

	/**
	 * name: nome di questo campo sulla tabella <br>
	 * nullable: se la foreign key può essere nulla
	 */
	@ManyToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "impiegato_id", nullable = false)
	private Impiegato impiegato;

	private LocalDate data;

	/**
	 * necessario per la relazione molti a molti e tabella relazionale <br>
	 * mappedBy: nome della proprietà (foreign key) della classe corrispondente (OrdineDettaglio.ordine)<br>
	 * JsonIgnore: non converte in json questa proprietà, che altrimenti va in loop
	 * infinito
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "ordine", fetch = FetchType.LAZY)
	private List<OrdineDettaglio> ordiniDettaglio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Impiegato getImpiegato() {
		return impiegato;
	}

	public void setImpiegato(Impiegato impiegato) {
		this.impiegato = impiegato;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<OrdineDettaglio> getOrdiniDettaglio() {
		return ordiniDettaglio;
	}

	public void setOrdiniDettaglio(List<OrdineDettaglio> ordiniDettaglio) {
		this.ordiniDettaglio = ordiniDettaglio;
	}



}
